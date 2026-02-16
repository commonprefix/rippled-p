package XrpPayment.pobserve.parser;

import XrpPayment.pobserve.PEvents;
import XrpPayment.pobserve.PTypes;
import pobserve.commons.PObserveEvent;
import pobserve.commons.Parser;
import pobserve.runtime.events.PEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Parses structured log lines emitted by rippled tests into PObserve events.
 *
 * Expected log format (pipe-delimited):
 *   POBSERVE|<timestamp_ms>|PAYMENT|sender=<id>|receiver=<id>|amount=<drops>|fee=<drops>|status=<SUCCESS|FAIL>|sbal_before=<drops>|sbal_after=<drops>|rbal_before=<drops>|rbal_after=<drops>
 *
 * Example:
 *   POBSERVE|1706000000000|PAYMENT|sender=0|receiver=1|amount=100000000|fee=10|status=SUCCESS|sbal_before=10000000000|sbal_after=9899999990|rbal_before=10000000000|rbal_after=10100000000
 *
 * Lines not starting with "POBSERVE|" are silently ignored (returns empty stream).
 */
public class RipplePaymentParser implements Parser<PEvent<?>> {

    // Maps base58 account addresses to sequential integer IDs for the P spec.
    // Also handles plain integer IDs (backward compat with test logs).
    private final Map<String, Long> accountIdMap = new HashMap<>();
    private long nextAccountId = 0;

    private long resolveAccountId(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return accountIdMap.computeIfAbsent(value, k -> nextAccountId++);
        }
    }

    @Override
    public Stream<PObserveEvent<PEvent<?>>> apply(Object logLineObj) {
        String logLine = logLineObj.toString().trim();

        if (!logLine.startsWith("POBSERVE|")) {
            return Stream.empty();
        }

        try {
            String[] parts = logLine.split("\\|");
            // parts[0] = "POBSERVE"
            // parts[1] = timestamp (ms)
            // parts[2] = event type ("PAYMENT")
            // parts[3..] = key=value pairs

            long timestamp = Long.parseLong(parts[1]);
            String eventType = parts[2];

            if (!"PAYMENT".equals(eventType)) {
                return Stream.empty();
            }

            long sender = 0, receiver = 0, amount = 0, fee = 0;
            long sbalBefore = 0, sbalAfter = 0, rbalBefore = 0, rbalAfter = 0;
            PTypes.tPaymentStatus status = PTypes.tPaymentStatus.SUCCESS;

            for (int i = 3; i < parts.length; i++) {
                String[] kv = parts[i].split("=", 2);
                if (kv.length != 2) continue;
                switch (kv[0]) {
                    case "sender":      sender     = resolveAccountId(kv[1]); break;
                    case "receiver":    receiver   = resolveAccountId(kv[1]); break;
                    case "amount":      amount     = Long.parseLong(kv[1]); break;
                    case "fee":         fee        = Long.parseLong(kv[1]); break;
                    case "status":      status     = "SUCCESS".equals(kv[1])
                                                     ? PTypes.tPaymentStatus.SUCCESS
                                                     : PTypes.tPaymentStatus.INSUFFICIENT_FUNDS; break;
                    case "sbal_before": sbalBefore = Long.parseLong(kv[1]); break;
                    case "sbal_after":  sbalAfter  = Long.parseLong(kv[1]); break;
                    case "rbal_before": rbalBefore = Long.parseLong(kv[1]); break;
                    case "rbal_after":  rbalAfter  = Long.parseLong(kv[1]); break;
                }
            }

            PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb payload =
                new PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb(
                    sender, receiver, amount, fee, status,
                    sbalBefore, sbalAfter, rbalBefore, rbalAfter
                );

            PEvents.ePaymentResp event = new PEvents.ePaymentResp(payload);

            // Partition key: "sender->receiver" so each account pair gets its own monitor
            String partitionKey = sender + "->" + receiver;

            PObserveEvent<PEvent<?>> pobserveEvent =
                new PObserveEvent<>(partitionKey, timestamp, event, logLine);

            return Stream.of(pobserveEvent);

        } catch (Exception e) {
            System.err.println("Failed to parse POBSERVE log line: " + logLine + " — " + e.getMessage());
            return Stream.empty();
        }
    }
}
