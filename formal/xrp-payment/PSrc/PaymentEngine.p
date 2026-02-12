// PaymentEngine.p
// Models the core XRP-to-XRP payment logic from rippled's Payment::doApply().
//
// In rippled (Payment.cpp):
//   1. Fee deducted first:  mSourceBalance = mPriorBalance - fee
//   2. Sufficiency check:   mPriorBalance >= amount + max(reserve, fee)
//   3. Transfer:            sender.balance = mSourceBalance - amount
//                           receiver.balance += amount
//
// Net effect on success: sender loses (amount + fee), receiver gains amount.

machine PaymentEngine {
    var balances: map[tAccountId, int];
    var fee: int;
    var reserve: int;
    var client: machine;

    start state Init {
        entry (config: (
            balances: map[tAccountId, int],
            fee: int,
            reserve: int,
            client: machine))
        {
            balances = config.balances;
            fee = config.fee;
            reserve = config.reserve;
            client = config.client;
            goto Ready;
        }
    }

    state Ready {
        on ePaymentReq do (req: tPaymentReq) {
            var senderBal: int;
            var receiverBal: int;
            var minRequired: int;

            senderBal = balances[req.sender];
            receiverBal = balances[req.receiver];

            // rippled: mPriorBalance < dstAmount + max(reserve, fee)
            if (reserve > fee) {
                minRequired = req.amount + reserve;
            } else {
                minRequired = req.amount + fee;
            }

            if (senderBal >= minRequired) {
                // Apply: deduct fee + amount from sender, credit receiver
                balances[req.sender] = senderBal - req.amount - fee;
                balances[req.receiver] = receiverBal + req.amount;

                send client, ePaymentResp, (
                    sender = req.sender,
                    receiver = req.receiver,
                    amount = req.amount,
                    fee = fee,
                    status = SUCCESS,
                    senderBalBefore = senderBal,
                    senderBalAfter = balances[req.sender],
                    receiverBalBefore = receiverBal,
                    receiverBalAfter = balances[req.receiver]
                );
            } else {
                // Insufficient funds — no state change
                send client, ePaymentResp, (
                    sender = req.sender,
                    receiver = req.receiver,
                    amount = req.amount,
                    fee = fee,
                    status = INSUFFICIENT_FUNDS,
                    senderBalBefore = senderBal,
                    senderBalAfter = senderBal,
                    receiverBalBefore = receiverBal,
                    receiverBalAfter = receiverBal
                );
            }
        }
    }
}
