// For a successful payment of amount N with fee F:
//   sender.after   == sender.before   - N - F
//   receiver.after == receiver.before + N
//
// For a failed payment:
//   No balances change.

spec XrpConservation observes ePaymentResp {

    start state Monitoring {
        on ePaymentResp do (resp: tPaymentResp) {
            if (resp.status == SUCCESS) {
                // Sender must lose exactly (amount + fee)
                assert resp.senderBalAfter == resp.senderBalBefore - resp.amount - resp.fee,
                    format("Sender balance wrong: {0} != {1} - {2} - {3}",
                        resp.senderBalAfter, resp.senderBalBefore, resp.amount, resp.fee);

                // Receiver must gain exactly amount
                assert resp.receiverBalAfter == resp.receiverBalBefore + resp.amount,
                    format("Receiver balance wrong: {0} != {1} + {2}",
                        resp.receiverBalAfter, resp.receiverBalBefore, resp.amount);
            } else {
                // Failed payment must not change any balance
                assert resp.senderBalAfter == resp.senderBalBefore,
                    "Sender balance changed on failed payment";
                assert resp.receiverBalAfter == resp.receiverBalBefore,
                    "Receiver balance changed on failed payment";
            }
        }
    }
}
