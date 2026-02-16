// TestDriver.p
// Non-deterministic test driver for XRP payments.
//
// Uses choose() and $ so the P checker explores all combinations of
// initial balances, payment choices, and rounds.

machine TestDriver {
    var engine: machine;
    var paymentsLeft: int;

    start state Init {
        entry {
            var balances: map[tAccountId, int];
            // choose(4) picks from {0,1,2,3}; scale to interesting balance range
            balances[0] = choose(4) * 3000;
            balances[1] = choose(4) * 3000;

            engine = new PaymentEngine((
                balances = balances,
                fee = 12,          // Example base fee (should definitely use choose!)
                reserve = 200,     // simplified reserve (move to choose)
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
                    // Payment A -> B (should succeed)
                    send engine, ePaymentReq,
                        (sender = 0, receiver = 1, amount = (choose(5) + 1) * 5000);
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
