package XrpPayment.pobserve;

/***************************************************************************
 * This file was auto-generated on Thursday, 12 February 2026 at 11:50:36.
 * Please do not edit manually!
 **************************************************************************/

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;

public class PEvents {
    public static class ePaymentResp extends pobserve.runtime.events.PEvent<PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb> implements Serializable {
        public ePaymentResp(PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb p) { this.payload = p; }
        private PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb payload; 
        public PTypes.PTuple_sndr_rcvr_amnt_fee_stts_sndrb_sndrb_rcvrb_rcvrb getPayload() { return payload; }
        
        @Override
        public String toString() { return "ePaymentResp[" + payload + "]"; }
    } // ePaymentResp
    
}
