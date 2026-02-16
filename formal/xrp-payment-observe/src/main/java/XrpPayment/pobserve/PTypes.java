package XrpPayment.pobserve;

/***************************************************************************
 * This file was auto-generated on Monday, 16 February 2026 at 10:44:26.
 * Please do not edit manually!
 **************************************************************************/

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;

public class PTypes {
    /* Enums */
    
    public enum tPaymentStatus {
        SUCCESS(0),
        INSUFFICIENT_FUNDS(1);
        private final int value;
        tPaymentStatus(int i) { value = i; }
    }
    
    /* Tuples */
    
    public static class PTuple_sndr_rcvr_amnt implements pobserve.runtime.values.PValue<PTuple_sndr_rcvr_amnt>, Serializable {
        // (sender:int,receiver:int,amount:int)
        public long sender;
        public long receiver;
        public long amount;
        
        public PTuple_sndr_rcvr_amnt() {
            this.sender = 0L;
            this.receiver = 0L;
            this.amount = 0L;
        }
        
        public PTuple_sndr_rcvr_amnt(long sender, long receiver, long amount) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
        }
        
        public PTuple_sndr_rcvr_amnt deepClone() {
            return new PTuple_sndr_rcvr_amnt(sender, receiver, amount);
        } // deepClone()
        
        
        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() && 
                this.deepEquals((PTuple_sndr_rcvr_amnt)other)
            );
        } // equals()
        
        public int hashCode() {
            return Objects.hash(sender, receiver, amount);
        } // hashCode()
        
        public boolean deepEquals(PTuple_sndr_rcvr_amnt other) {
            return (true
                 && this.sender == other.sender
                 && this.receiver == other.receiver
                 && this.amount == other.amount
            );
        } // deepEquals()
        
        
        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_sndr_rcvr_amnt");
            sb.append("[");
            sb.append("sender=" + sender);
            sb.append(", receiver=" + receiver);
            sb.append(", amount=" + amount);
            sb.append("]");
            return sb.toString();
        } // toString()
        
    } //PTuple_sndr_rcvr_amnt class definition
    
    public static class PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb implements pobserve.runtime.values.PValue<PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb>, Serializable {
        // (sender:int,receiver:int,amount:int,fee:int,status:tPaymentStatus,senderBalBefore:int,senderBalAfter:int,receiverBalBefore:int,receiverBalAfter:int)
        public long sender;
        public long receiver;
        public long amount;
        public long fee;
        public PTypes.tPaymentStatus status;
        public long senderBalBefore;
        public long senderBalAfter;
        public long receiverBalBefore;
        public long receiverBalAfter;
        
        public PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb() {
            this.sender = 0L;
            this.receiver = 0L;
            this.amount = 0L;
            this.fee = 0L;
            this.status = PTypes.tPaymentStatus.SUCCESS;
            this.senderBalBefore = 0L;
            this.senderBalAfter = 0L;
            this.receiverBalBefore = 0L;
            this.receiverBalAfter = 0L;
        }
        
        public PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb(long sender, long receiver, long amount, long fee, PTypes.tPaymentStatus status, long senderBalBefore, long senderBalAfter, long receiverBalBefore, long receiverBalAfter) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
            this.fee = fee;
            this.status = status;
            this.senderBalBefore = senderBalBefore;
            this.senderBalAfter = senderBalAfter;
            this.receiverBalBefore = receiverBalBefore;
            this.receiverBalAfter = receiverBalAfter;
        }
        
        public PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb deepClone() {
            return new PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb(sender, receiver, amount, fee, status, senderBalBefore, senderBalAfter, receiverBalBefore, receiverBalAfter);
        } // deepClone()
        
        
        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() && 
                this.deepEquals((PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb)other)
            );
        } // equals()
        
        public int hashCode() {
            return Objects.hash(sender, receiver, amount, fee, status, senderBalBefore, senderBalAfter, receiverBalBefore, receiverBalAfter);
        } // hashCode()
        
        public boolean deepEquals(PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb other) {
            return (true
                 && this.sender == other.sender
                 && this.receiver == other.receiver
                 && this.amount == other.amount
                 && this.fee == other.fee
                 && this.status == other.status
                 && this.senderBalBefore == other.senderBalBefore
                 && this.senderBalAfter == other.senderBalAfter
                 && this.receiverBalBefore == other.receiverBalBefore
                 && this.receiverBalAfter == other.receiverBalAfter
            );
        } // deepEquals()
        
        
        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb");
            sb.append("[");
            sb.append("sender=" + sender);
            sb.append(", receiver=" + receiver);
            sb.append(", amount=" + amount);
            sb.append(", fee=" + fee);
            sb.append(", status=" + status);
            sb.append(", senderBalBefore=" + senderBalBefore);
            sb.append(", senderBalAfter=" + senderBalAfter);
            sb.append(", receiverBalBefore=" + receiverBalBefore);
            sb.append(", receiverBalAfter=" + receiverBalAfter);
            sb.append("]");
            return sb.toString();
        } // toString()
        
    } //PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb class definition
    
    public static class PTuple_blncs_fee_rsrv_clnt implements pobserve.runtime.values.PValue<PTuple_blncs_fee_rsrv_clnt>, Serializable {
        // (balances:map[int,int],fee:int,reserve:int,client:machine)
        public HashMap<Long, Long> balances;
        public long fee;
        public long reserve;
        public long client;
        
        public PTuple_blncs_fee_rsrv_clnt() {
            this.balances = new HashMap<Long, Long>();
            this.fee = 0L;
            this.reserve = 0L;
            this.client = 0L;
        }
        
        public PTuple_blncs_fee_rsrv_clnt(HashMap<Long, Long> balances, long fee, long reserve, long client) {
            this.balances = balances;
            this.fee = fee;
            this.reserve = reserve;
            this.client = client;
        }
        
        public PTuple_blncs_fee_rsrv_clnt deepClone() {
            return new PTuple_blncs_fee_rsrv_clnt(pobserve.runtime.values.Clone.deepClone(balances), fee, reserve, client);
        } // deepClone()
        
        
        public boolean equals(Object other) {
            return (this.getClass() == other.getClass() && 
                this.deepEquals((PTuple_blncs_fee_rsrv_clnt)other)
            );
        } // equals()
        
        public int hashCode() {
            return Objects.hash(balances, fee, reserve, client);
        } // hashCode()
        
        public boolean deepEquals(PTuple_blncs_fee_rsrv_clnt other) {
            return (true
                 && pobserve.runtime.values.Equality.deepEquals(this.balances, other.balances)
                 && this.fee == other.fee
                 && this.reserve == other.reserve
                 && this.client == other.client
            );
        } // deepEquals()
        
        
        public String toString() {
            StringBuilder sb = new StringBuilder("PTuple_blncs_fee_rsrv_clnt");
            sb.append("[");
            sb.append("balances=" + balances);
            sb.append(", fee=" + fee);
            sb.append(", reserve=" + reserve);
            sb.append(", client=" + client);
            sb.append("]");
            return sb.toString();
        } // toString()
        
    } //PTuple_blncs_fee_rsrv_clnt class definition
    
    
}
