// PaymentTypes.p
// Types and events modeling XRP-to-XRP direct payments in rippled.
//
// Simplifications vs. actual rippled:
//   - No destination tag / deposit auth / credential checks
//   - Failed payments don't charge fee (rippled tec results do charge fee)
//   - No account creation (destination must pre-exist)
//   - Reserve is a flat value, not based on owner count

type tAccountId = int;

type tPaymentReq = (
    sender: tAccountId,
    receiver: tAccountId,
    amount: int
);

enum tPaymentStatus {
    SUCCESS,
    INSUFFICIENT_FUNDS
}

type tPaymentResp = (
    sender: tAccountId,
    receiver: tAccountId,
    amount: int,
    fee: int,
    status: tPaymentStatus,
    senderBalBefore: int,
    senderBalAfter: int,
    receiverBalBefore: int,
    receiverBalAfter: int
);

event ePaymentReq: tPaymentReq;
event ePaymentResp: tPaymentResp;
