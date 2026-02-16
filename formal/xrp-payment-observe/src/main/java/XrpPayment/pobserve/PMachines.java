package XrpPayment.pobserve;

/***************************************************************************
 * This file was auto-generated on Monday, 16 February 2026 at 12:17:45.
 * Please do not edit manually!
 **************************************************************************/

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;

public class PMachines {
    private static Logger logger = Logger.getLogger(PMachines.class.getName());
    static { logger.setLevel(Level.OFF); };
    // StateMachine PaymentEngine elided 
    public static class XrpConservation extends pobserve.runtime.Monitor<XrpConservation.PrtStates> implements Serializable {
        
        public static class Supplier implements java.util.function.Supplier<XrpConservation>, Serializable {
            public XrpConservation get() {
                XrpConservation ret = new XrpConservation();
                ret.ready();
                return ret;
            }
        }
        
        
        public enum PrtStates {
            Monitoring
        }
        
        public XrpConservation() {
            super();
            addState(pobserve.runtime.State.keyedOn(PrtStates.Monitoring)
                .isInitialState(true)
                .withEvent(PEvents.ePaymentResp.class, this::Anon)
                .build());
        } // constructor
        
        public void reInitializeMonitor() {
            registerState(pobserve.runtime.State.keyedOn(PrtStates.Monitoring)
                .isInitialState(true)
                .withEvent(PEvents.ePaymentResp.class, this::Anon)
                .build());
        }
        
        public java.util.List<Class<? extends pobserve.runtime.events.PEvent<?>>> getEventTypes() {
            return java.util.Arrays.asList(PEvents.ePaymentResp.class);
        }
        
        private void Anon(PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb resp) {
            PTypes.tPaymentStatus TMP_tmp0;
            boolean TMP_tmp1;
            long TMP_tmp2;
            long TMP_tmp3;
            long TMP_tmp4;
            long TMP_tmp5;
            long TMP_tmp6;
            long TMP_tmp7;
            boolean TMP_tmp8;
            String TMP_tmp9;
            long TMP_tmp10;
            long TMP_tmp11;
            long TMP_tmp12;
            long TMP_tmp13;
            String TMP_tmp14;
            String TMP_tmp15;
            long TMP_tmp16;
            long TMP_tmp17;
            long TMP_tmp18;
            long TMP_tmp19;
            boolean TMP_tmp20;
            String TMP_tmp21;
            long TMP_tmp22;
            long TMP_tmp23;
            long TMP_tmp24;
            String TMP_tmp25;
            String TMP_tmp26;
            long TMP_tmp27;
            long TMP_tmp28;
            boolean TMP_tmp29;
            String TMP_tmp30;
            String TMP_tmp31;
            String TMP_tmp32;
            long TMP_tmp33;
            long TMP_tmp34;
            boolean TMP_tmp35;
            String TMP_tmp36;
            String TMP_tmp37;
            String TMP_tmp38;
            
            TMP_tmp0 = resp.status;
            TMP_tmp1 = TMP_tmp0 == PTypes.tPaymentStatus.SUCCESS;
            if (TMP_tmp1) {
                TMP_tmp2 = resp.senderBalAfter;
                TMP_tmp3 = resp.senderBalBefore;
                TMP_tmp4 = resp.amount;
                TMP_tmp5 = TMP_tmp3 - TMP_tmp4;
                TMP_tmp6 = resp.fee;
                TMP_tmp7 = TMP_tmp5 - TMP_tmp6;
                TMP_tmp8 = TMP_tmp2 == TMP_tmp7;
                if (TMP_tmp8) {
                }
                else
                {
                    TMP_tmp9 = "PSpec/XrpConservation.p:17:17";
                    TMP_tmp10 = resp.senderBalAfter;
                    TMP_tmp11 = resp.senderBalBefore;
                    TMP_tmp12 = resp.amount;
                    TMP_tmp13 = resp.fee;
                    TMP_tmp14 = java.text.MessageFormat.format("Sender balance wrong: {0} != {1} - {2} - {3}", TMP_tmp10, TMP_tmp11, TMP_tmp12, TMP_tmp13);
                    TMP_tmp15 = java.text.MessageFormat.format("{0} {1}", TMP_tmp9, TMP_tmp14);
                    tryAssert(TMP_tmp8, TMP_tmp15);
                }
                TMP_tmp16 = resp.receiverBalAfter;
                TMP_tmp17 = resp.receiverBalBefore;
                TMP_tmp18 = resp.amount;
                TMP_tmp19 = TMP_tmp17 + TMP_tmp18;
                TMP_tmp20 = TMP_tmp16 == TMP_tmp19;
                if (TMP_tmp20) {
                }
                else
                {
                    TMP_tmp21 = "PSpec/XrpConservation.p:22:17";
                    TMP_tmp22 = resp.receiverBalAfter;
                    TMP_tmp23 = resp.receiverBalBefore;
                    TMP_tmp24 = resp.amount;
                    TMP_tmp25 = java.text.MessageFormat.format("Receiver balance wrong: {0} != {1} + {2}", TMP_tmp22, TMP_tmp23, TMP_tmp24);
                    TMP_tmp26 = java.text.MessageFormat.format("{0} {1}", TMP_tmp21, TMP_tmp25);
                    tryAssert(TMP_tmp20, TMP_tmp26);
                }
            }
            else
            {
                TMP_tmp27 = resp.senderBalAfter;
                TMP_tmp28 = resp.senderBalBefore;
                TMP_tmp29 = TMP_tmp27 == TMP_tmp28;
                if (TMP_tmp29) {
                }
                else
                {
                    TMP_tmp30 = "PSpec/XrpConservation.p:27:17";
                    TMP_tmp31 = "Sender balance changed on failed payment";
                    TMP_tmp32 = java.text.MessageFormat.format("{0} {1}", TMP_tmp30, TMP_tmp31);
                    tryAssert(TMP_tmp29, TMP_tmp32);
                }
                TMP_tmp33 = resp.receiverBalAfter;
                TMP_tmp34 = resp.receiverBalBefore;
                TMP_tmp35 = TMP_tmp33 == TMP_tmp34;
                if (TMP_tmp35) {
                }
                else
                {
                    TMP_tmp36 = "PSpec/XrpConservation.p:29:17";
                    TMP_tmp37 = "Receiver balance changed on failed payment";
                    TMP_tmp38 = java.text.MessageFormat.format("{0} {1}", TMP_tmp36, TMP_tmp37);
                    tryAssert(TMP_tmp35, TMP_tmp38);
                }
            }
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder("XrpConservation");
            sb.append("[");
            sb.append("]");
            return sb.toString();
        } // toString()
        
        public boolean deepEquals(XrpConservation other) {
            return (true
            );
        } // deepEquals()
        
        public boolean equals(Object other) {
            return (this.getClass() == other.getClass()) && this.deepEquals((XrpConservation)other);
        } // equals()
        
        public int hashCode() {
            return Objects.hash();
        } // hashCode()
        
    } // XrpConservation monitor definition
    // StateMachine TestDriver elided 
}
