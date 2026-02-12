// TestDriver.p
// Non-deterministic test driver for XRP payments.
//
// Uses $ (non-deterministic boolean) so the P checker explores all
// combinations: 4 payment choices x 3 rounds = 64 execution paths,
// covering success, failure, and both directions (A->B, B->A).

machine TestDriver {
    var engine: machine;
    var paymentsLeft: int;

    start state Init {
        entry {
            var balances: map[tAccountId, int];
            balances[0] = 10000;   // Account A: 10,000 drops
            balances[1] = 5000;    // Account B: 5,000 drops

            engine = new PaymentEngine((
                balances = balances,
                fee = 12,          // 12 drops (typical base fee)
                reserve = 200,     // simplified reserve
                client = this
            ));

            paymentsLeft = 3;
            goto SendPayments;
        }
    }

    state SendPayments {
        entry {
            if (paymentsLeft > 0) {
                if ($) {
                    // Small payment A -> B (should succeed)
                    send engine, ePaymentReq,
                        (sender = 0, receiver = 1, amount = 500);
                } else if ($) {
                    // Large payment A -> B (may fail after prior debits)
                    send engine, ePaymentReq,
                        (sender = 0, receiver = 1, amount = 9000);
                } else if ($) {
                    // Reverse direction: B -> A
                    send engine, ePaymentReq,
                        (sender = 1, receiver = 0, amount = 1000);
                } else {
                    // Way over balance (must fail)
                    send engine, ePaymentReq,
                        (sender = 0, receiver = 1, amount = 100000);
                }
                paymentsLeft = paymentsLeft - 1;
            }
        }

        on ePaymentResp do {
            goto SendPayments;
        }
    }
}

// The test: run TestDriver + PaymentEngine, assert XrpConservation holds.
test testXrpPayment [main=TestDriver]:
    assert XrpConservation in (union { TestDriver }, { PaymentEngine });
