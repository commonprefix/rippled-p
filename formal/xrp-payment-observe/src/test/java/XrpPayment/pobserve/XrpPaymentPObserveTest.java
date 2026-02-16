package XrpPayment.pobserve;

import XrpPayment.pobserve.parser.RipplePaymentParser;
import pobserve.commons.PObserveEvent;
import pobserve.runtime.events.PEvent;
import pobserve.runtime.exceptions.PAssertionFailureException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * Tests that XrpConservation spec holds on rippled payment logs.
 *
 * This is the PObserve integration: the same spec written in P
 * (formal/xrp-payment/PSpec/XrpConservation.p) is now compiled to Java
 * and checked against real log output from rippled tests.
 */
public class XrpPaymentPObserveTest {

    /**
     * Feed a valid payment log through the XrpConservation monitor.
     * All 3 payments (2 success, 1 failure) should pass the spec.
     */
    @Test
    public void testValidPaymentLog() throws Exception {
        PMachines.XrpConservation monitor = new PMachines.XrpConservation.Supplier().get();
        RipplePaymentParser parser = new RipplePaymentParser();

        InputStream is = getClass().getClassLoader().getResourceAsStream("sample_payment.log");
        assertNotNull(is, "sample_payment.log not found in test resources");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        int eventCount = 0;

        while ((line = reader.readLine()) != null) {
            Stream<PObserveEvent<PEvent<?>>> events = parser.apply(line);
            for (PObserveEvent<PEvent<?>> evt : (Iterable<PObserveEvent<PEvent<?>>>) events::iterator) {
                // Feed the event into the XrpConservation monitor
                // If the spec is violated, this throws PAssertionFailureException
                monitor.accept(evt.getEvent());
                eventCount++;
            }
        }

        assertEquals(3, eventCount, "Expected 3 payment events from sample log");
        System.out.println("XrpConservation spec passed on " + eventCount + " events.");
    }

    /**
     * Feed a WRONG payment log — receiver gets more than they should.
     * The XrpConservation spec must catch this violation.
     */
    @Test
    public void testBadPaymentDetected() {
        PMachines.XrpConservation monitor = new PMachines.XrpConservation.Supplier().get();
        RipplePaymentParser parser = new RipplePaymentParser();

        // Receiver gains 200000000 but amount is only 100000000 — spec violation!
        String badLog = "POBSERVE|1706000001000|PAYMENT|sender=0|receiver=1"
            + "|amount=100000000|fee=10|status=SUCCESS"
            + "|sbal_before=10000000000|sbal_after=9899999990"
            + "|rbal_before=10000000000|rbal_after=10200000000";

        Stream<PObserveEvent<PEvent<?>>> events = parser.apply(badLog);
        PObserveEvent<PEvent<?>> evt = events.findFirst().orElseThrow();

        PAssertionFailureException ex = assertThrows(
            PAssertionFailureException.class,
            () -> monitor.accept(evt.getEvent()),
            "XrpConservation should catch the balance violation"
        );

        System.out.println("Correctly caught violation: " + ex.getMessage());
        assertTrue(ex.getMessage().contains("Receiver balance wrong"));
    }

    /**
     * Non-POBSERVE lines should be silently ignored by the parser.
     */
    @Test
    public void testNonPobserveLineIgnored() {
        RipplePaymentParser parser = new RipplePaymentParser();
        assertEquals(0, parser.apply("some random rippled log line").count());
        assertEquals(0, parser.apply("").count());
        assertEquals(0, parser.apply("2024-01-23 INFO Payment applied").count());
    }

    /**
     * Integration test: reads the log file produced by rippled's PObservePayment_test.
     *
     * To run the full pipeline:
     *   1. Build and run the C++ test:
     *        ./rippled --unittest=PObservePayment
     *      This writes /tmp/pobserve_payment.log
     *   2. Run this test:
     *        mvn test -Dtest=XrpPaymentPObserveTest#testRippledOutput -Dpobserve.logfile=/tmp/pobserve_payment.log
     *
     * Skipped if the log file doesn't exist (C++ test hasn't been run).
     */
    @Test
    public void testRippledOutput() throws Exception {
        String logPath = System.getProperty("pobserve.logfile", "/tmp/pobserve_payment.log");
        File logFile = new File(logPath);

        if (!logFile.exists()) {
            System.out.println("SKIPPED: " + logPath + " not found. "
                + "Run './rippled --unittest=PObservePayment' first.");
            return;
        }

        PMachines.XrpConservation monitor = new PMachines.XrpConservation.Supplier().get();
        RipplePaymentParser parser = new RipplePaymentParser();

        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        String line;
        int eventCount = 0;

        while ((line = reader.readLine()) != null) {
            Stream<PObserveEvent<PEvent<?>>> events = parser.apply(line);
            for (PObserveEvent<PEvent<?>> evt : (Iterable<PObserveEvent<PEvent<?>>>) events::iterator) {
                monitor.accept(evt.getEvent());
                eventCount++;
            }
        }
        reader.close();

        assertTrue(eventCount > 0, "Expected at least one POBSERVE event in " + logPath);
        System.out.println("XrpConservation spec passed on " + eventCount
            + " events from rippled output (" + logPath + ")");
    }
}
