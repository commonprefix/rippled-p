// Types and events modeling XRP-to-XRP direct payments in rippled.
// Simplified - proof of concept for PObserve

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
