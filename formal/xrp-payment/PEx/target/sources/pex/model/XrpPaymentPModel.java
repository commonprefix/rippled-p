package pex.model;

import pex.runtime.*;
import pex.runtime.logger.*;
import pex.runtime.machine.*;
import pex.runtime.machine.buffer.*;
import pex.runtime.machine.eventhandlers.*;
import pex.runtime.machine.events.*;
import pex.values.*;
import pex.utils.*;
import pex.utils.misc.*;
import pex.utils.serialize.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.text.MessageFormat;
import lombok.Generated;

public class XrpPaymentPModel implements PModel {
    
    
    
    @Generated
    public static class GlobalConfig
    {
    }
    
    // Skipping EnumElem 'SUCCESS'

    // Skipping EnumElem 'INSUFFICIENT_FUNDS'

    // Skipping PEnum 'tPaymentStatus'

    public static Event _null = new Event("_null");
    public static Event _halt = new Event("_halt");
    public static Event ePaymentReq = new Event("ePaymentReq");
    public static Event ePaymentResp = new Event("ePaymentResp");
    // Skipping Interface 'PaymentEngine'

    // Skipping Interface 'TestDriver'

    public static class PaymentEngine extends PMachine {
        
        static State Init = new State("Init", "PaymentEngine", StateTemperature.Warm) {
            @Generated
            @Override
            public void entry(PMachine machine, PValue<?> payload) {
                super.entry(machine, payload);
                ((PaymentEngine)machine).Init_entry(machine, (PNamedTuple) payload);
            }
        };
        static State Ready = new State("Ready", "PaymentEngine", StateTemperature.Warm) {
        };
        public PMap var_balances = new PMap();
        public PInt var_fee = new PInt(0);
        public PInt var_reserve = new PInt(0);
        public PMachineValue var_client = null;
        
        public PaymentEngine(int id) {
            super("PaymentEngine", id, Init, Init
                , Ready
                
            );
            Init.registerHandlers(
            );
            Ready.registerHandlers(
                new EventHandler(ePaymentReq) {
                    @Override public void handleEvent(PMachine machine, PValue<?> payload) {
                        ((PaymentEngine)machine).Ready_ePaymentReq(machine, (PNamedTuple) payload);
                    }
                });
        }
        
        void 
        Init_entry(
            PMachine currentMachine,
            PNamedTuple var_config
        ) {
            PMap var_$tmp0 =
                new PMap();
            
            PMap var_$tmp1 =
                new PMap();
            
            PInt var_$tmp2 =
                new PInt(0);
            
            PInt var_$tmp3 =
                new PInt(0);
            
            PInt var_$tmp4 =
                new PInt(0);
            
            PInt var_$tmp5 =
                new PInt(0);
            
            PMachineValue var_$tmp6 =
                null;
            
            PMachineValue var_$tmp7 =
                null;
            
            PMap temp_var_0;
            temp_var_0 = (PMap) ((PMap)(((PNamedTuple)var_config).getField("balances")));
            var_$tmp0 = temp_var_0;
            
            PMap temp_var_1;
            temp_var_1 = (PMap) var_$tmp0;
            var_$tmp1 = temp_var_1;
            
            PMap temp_var_2;
            temp_var_2 = (PMap) var_$tmp1;
            var_balances = temp_var_2;
            
            PInt temp_var_3;
            temp_var_3 = (PInt) ((PInt)(((PNamedTuple)var_config).getField("fee")));
            var_$tmp2 = temp_var_3;
            
            PInt temp_var_4;
            temp_var_4 = (PInt) var_$tmp2;
            var_$tmp3 = temp_var_4;
            
            PInt temp_var_5;
            temp_var_5 = (PInt) var_$tmp3;
            var_fee = temp_var_5;
            
            PInt temp_var_6;
            temp_var_6 = (PInt) ((PInt)(((PNamedTuple)var_config).getField("reserve")));
            var_$tmp4 = temp_var_6;
            
            PInt temp_var_7;
            temp_var_7 = (PInt) var_$tmp4;
            var_$tmp5 = temp_var_7;
            
            PInt temp_var_8;
            temp_var_8 = (PInt) var_$tmp5;
            var_reserve = temp_var_8;
            
            PMachineValue temp_var_9;
            temp_var_9 = (PMachineValue) ((PMachineValue)(((PNamedTuple)var_config).getField("client")));
            var_$tmp6 = temp_var_9;
            
            PMachineValue temp_var_10;
            temp_var_10 = (PMachineValue) var_$tmp6;
            var_$tmp7 = temp_var_10;
            
            PMachineValue temp_var_11;
            temp_var_11 = (PMachineValue) var_$tmp7;
            var_client = temp_var_11;
            
            currentMachine.gotoState(Ready, null);
            return;
            
        }
        
        void 
        Ready_ePaymentReq(
            PMachine currentMachine,
            PNamedTuple var_req
        ) {
            PInt var_senderBal =
                new PInt(0);
            
            PInt var_receiverBal =
                new PInt(0);
            
            PInt var_minRequired =
                new PInt(0);
            
            PInt var_$tmp0 =
                new PInt(0);
            
            PInt var_$tmp1 =
                new PInt(0);
            
            PInt var_$tmp2 =
                new PInt(0);
            
            PInt var_$tmp3 =
                new PInt(0);
            
            PInt var_$tmp4 =
                new PInt(0);
            
            PInt var_$tmp5 =
                new PInt(0);
            
            PBool var_$tmp6 =
                new PBool(false);
            
            PInt var_$tmp7 =
                new PInt(0);
            
            PInt var_$tmp8 =
                new PInt(0);
            
            PInt var_$tmp9 =
                new PInt(0);
            
            PInt var_$tmp10 =
                new PInt(0);
            
            PBool var_$tmp11 =
                new PBool(false);
            
            PInt var_$tmp12 =
                new PInt(0);
            
            PInt var_$tmp13 =
                new PInt(0);
            
            PInt var_$tmp14 =
                new PInt(0);
            
            PInt var_$tmp15 =
                new PInt(0);
            
            PInt var_$tmp16 =
                new PInt(0);
            
            PInt var_$tmp17 =
                new PInt(0);
            
            PInt var_$tmp18 =
                new PInt(0);
            
            PMachineValue var_$tmp19 =
                null;
            
            Event var_$tmp20 =
                null;
            
            PInt var_$tmp21 =
                new PInt(0);
            
            PInt var_$tmp22 =
                new PInt(0);
            
            PInt var_$tmp23 =
                new PInt(0);
            
            PInt var_$tmp24 =
                new PInt(0);
            
            PEnum var_$tmp25 =
                new PEnum("tPaymentStatus", "SUCCESS", 0);
            
            PInt var_$tmp26 =
                new PInt(0);
            
            PInt var_$tmp27 =
                new PInt(0);
            
            PInt var_$tmp28 =
                new PInt(0);
            
            PInt var_$tmp29 =
                new PInt(0);
            
            PInt var_$tmp30 =
                new PInt(0);
            
            PInt var_$tmp31 =
                new PInt(0);
            
            PNamedTuple var_$tmp32 =
                new PNamedTuple(List.of("sender", "receiver", "amount", "fee", "status", "senderBalBefore", "senderBalAfter", "receiverBalBefore", "receiverBalAfter"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0), new PInt(0), new PEnum("tPaymentStatus", "SUCCESS", 0), new PInt(0), new PInt(0), new PInt(0), new PInt(0)));
            
            PMachineValue var_$tmp33 =
                null;
            
            Event var_$tmp34 =
                null;
            
            PInt var_$tmp35 =
                new PInt(0);
            
            PInt var_$tmp36 =
                new PInt(0);
            
            PInt var_$tmp37 =
                new PInt(0);
            
            PInt var_$tmp38 =
                new PInt(0);
            
            PEnum var_$tmp39 =
                new PEnum("tPaymentStatus", "SUCCESS", 0);
            
            PInt var_$tmp40 =
                new PInt(0);
            
            PInt var_$tmp41 =
                new PInt(0);
            
            PInt var_$tmp42 =
                new PInt(0);
            
            PInt var_$tmp43 =
                new PInt(0);
            
            PNamedTuple var_$tmp44 =
                new PNamedTuple(List.of("sender", "receiver", "amount", "fee", "status", "senderBalBefore", "senderBalAfter", "receiverBalBefore", "receiverBalAfter"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0), new PInt(0), new PEnum("tPaymentStatus", "SUCCESS", 0), new PInt(0), new PInt(0), new PInt(0), new PInt(0)));
            
            PInt temp_var_12;
            temp_var_12 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("sender")));
            var_$tmp0 = temp_var_12;
            
            PInt temp_var_13;
            temp_var_13 = (PInt) ((PMap)var_balances).get(var_$tmp0);
            var_$tmp1 = temp_var_13;
            
            PInt temp_var_14;
            temp_var_14 = (PInt) var_$tmp1;
            var_$tmp2 = temp_var_14;
            
            PInt temp_var_15;
            temp_var_15 = (PInt) var_$tmp2;
            var_senderBal = temp_var_15;
            
            PInt temp_var_16;
            temp_var_16 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("receiver")));
            var_$tmp3 = temp_var_16;
            
            PInt temp_var_17;
            temp_var_17 = (PInt) ((PMap)var_balances).get(var_$tmp3);
            var_$tmp4 = temp_var_17;
            
            PInt temp_var_18;
            temp_var_18 = (PInt) var_$tmp4;
            var_$tmp5 = temp_var_18;
            
            PInt temp_var_19;
            temp_var_19 = (PInt) var_$tmp5;
            var_receiverBal = temp_var_19;
            
            PBool temp_var_20;
            temp_var_20 = (PBool) (var_reserve).gt(var_fee);
            var_$tmp6 = temp_var_20;
            
            PBool temp_var_21 = var_$tmp6;
            if (temp_var_21.getValue()) {
                // 'then' branch
                PInt temp_var_22;
                temp_var_22 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp7 = temp_var_22;
                
                PInt temp_var_23;
                temp_var_23 = (PInt) (var_$tmp7).add(var_reserve);
                var_$tmp8 = temp_var_23;
                
                PInt temp_var_24;
                temp_var_24 = (PInt) var_$tmp8;
                var_minRequired = temp_var_24;
                
            }
            else {
                // 'else' branch
                PInt temp_var_25;
                temp_var_25 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp9 = temp_var_25;
                
                PInt temp_var_26;
                temp_var_26 = (PInt) (var_$tmp9).add(var_fee);
                var_$tmp10 = temp_var_26;
                
                PInt temp_var_27;
                temp_var_27 = (PInt) var_$tmp10;
                var_minRequired = temp_var_27;
                
            }
            
            PBool temp_var_28;
            temp_var_28 = (PBool) (var_senderBal).ge(var_minRequired);
            var_$tmp11 = temp_var_28;
            
            PBool temp_var_29 = var_$tmp11;
            if (temp_var_29.getValue()) {
                // 'then' branch
                PInt temp_var_30;
                temp_var_30 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("sender")));
                var_$tmp12 = temp_var_30;
                
                PInt temp_var_31;
                temp_var_31 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp13 = temp_var_31;
                
                PInt temp_var_32;
                temp_var_32 = (PInt) (var_senderBal).sub(var_$tmp13);
                var_$tmp14 = temp_var_32;
                
                PInt temp_var_33;
                temp_var_33 = (PInt) (var_$tmp14).sub(var_fee);
                var_$tmp15 = temp_var_33;
                
                PMap temp_var_34 = (PMap) var_balances;    
                PInt temp_var_36 = var_$tmp12;
                PInt temp_var_35;
                temp_var_35 = (PInt) var_$tmp15;
                temp_var_34 = temp_var_34.put(temp_var_36, temp_var_35);
                var_balances = temp_var_34;
                
                PInt temp_var_37;
                temp_var_37 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("receiver")));
                var_$tmp16 = temp_var_37;
                
                PInt temp_var_38;
                temp_var_38 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp17 = temp_var_38;
                
                PInt temp_var_39;
                temp_var_39 = (PInt) (var_receiverBal).add(var_$tmp17);
                var_$tmp18 = temp_var_39;
                
                PMap temp_var_40 = (PMap) var_balances;    
                PInt temp_var_42 = var_$tmp16;
                PInt temp_var_41;
                temp_var_41 = (PInt) var_$tmp18;
                temp_var_40 = temp_var_40.put(temp_var_42, temp_var_41);
                var_balances = temp_var_40;
                
                PMachineValue temp_var_43;
                temp_var_43 = (PMachineValue) var_client;
                var_$tmp19 = temp_var_43;
                
                Event temp_var_44;
                temp_var_44 = (Event) new Event(ePaymentResp);
                var_$tmp20 = temp_var_44;
                
                PInt temp_var_45;
                temp_var_45 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("sender")));
                var_$tmp21 = temp_var_45;
                
                PInt temp_var_46;
                temp_var_46 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("receiver")));
                var_$tmp22 = temp_var_46;
                
                PInt temp_var_47;
                temp_var_47 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp23 = temp_var_47;
                
                PInt temp_var_48;
                temp_var_48 = (PInt) var_fee;
                var_$tmp24 = temp_var_48;
                
                PEnum temp_var_49;
                temp_var_49 = (PEnum) new PEnum("tPaymentStatus", "SUCCESS", 0);
                var_$tmp25 = temp_var_49;
                
                PInt temp_var_50;
                temp_var_50 = (PInt) var_senderBal;
                var_$tmp26 = temp_var_50;
                
                PInt temp_var_51;
                temp_var_51 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("sender")));
                var_$tmp27 = temp_var_51;
                
                PInt temp_var_52;
                temp_var_52 = (PInt) ((PMap)var_balances).get(var_$tmp27);
                var_$tmp28 = temp_var_52;
                
                PInt temp_var_53;
                temp_var_53 = (PInt) var_receiverBal;
                var_$tmp29 = temp_var_53;
                
                PInt temp_var_54;
                temp_var_54 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("receiver")));
                var_$tmp30 = temp_var_54;
                
                PInt temp_var_55;
                temp_var_55 = (PInt) ((PMap)var_balances).get(var_$tmp30);
                var_$tmp31 = temp_var_55;
                
                PNamedTuple temp_var_56;
                temp_var_56 = (PNamedTuple) new PNamedTuple(
                    List.of("sender", "receiver", "amount", "fee", "status", "senderBalBefore", "senderBalAfter", "receiverBalBefore", "receiverBalAfter"), 
                    Arrays.asList(var_$tmp21, var_$tmp22, var_$tmp23, var_$tmp24, var_$tmp25, var_$tmp26, var_$tmp28, var_$tmp29, var_$tmp31)
                )
                ;
                var_$tmp32 = temp_var_56;
                
                currentMachine.sendEvent(var_$tmp19, var_$tmp20, var_$tmp32);
                
            }
            else {
                // 'else' branch
                PMachineValue temp_var_57;
                temp_var_57 = (PMachineValue) var_client;
                var_$tmp33 = temp_var_57;
                
                Event temp_var_58;
                temp_var_58 = (Event) new Event(ePaymentResp);
                var_$tmp34 = temp_var_58;
                
                PInt temp_var_59;
                temp_var_59 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("sender")));
                var_$tmp35 = temp_var_59;
                
                PInt temp_var_60;
                temp_var_60 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("receiver")));
                var_$tmp36 = temp_var_60;
                
                PInt temp_var_61;
                temp_var_61 = (PInt) ((PInt)(((PNamedTuple)var_req).getField("amount")));
                var_$tmp37 = temp_var_61;
                
                PInt temp_var_62;
                temp_var_62 = (PInt) var_fee;
                var_$tmp38 = temp_var_62;
                
                PEnum temp_var_63;
                temp_var_63 = (PEnum) new PEnum("tPaymentStatus", "INSUFFICIENT_FUNDS", 1);
                var_$tmp39 = temp_var_63;
                
                PInt temp_var_64;
                temp_var_64 = (PInt) var_senderBal;
                var_$tmp40 = temp_var_64;
                
                PInt temp_var_65;
                temp_var_65 = (PInt) var_senderBal;
                var_$tmp41 = temp_var_65;
                
                PInt temp_var_66;
                temp_var_66 = (PInt) var_receiverBal;
                var_$tmp42 = temp_var_66;
                
                PInt temp_var_67;
                temp_var_67 = (PInt) var_receiverBal;
                var_$tmp43 = temp_var_67;
                
                PNamedTuple temp_var_68;
                temp_var_68 = (PNamedTuple) new PNamedTuple(
                    List.of("sender", "receiver", "amount", "fee", "status", "senderBalBefore", "senderBalAfter", "receiverBalBefore", "receiverBalAfter"), 
                    Arrays.asList(var_$tmp35, var_$tmp36, var_$tmp37, var_$tmp38, var_$tmp39, var_$tmp40, var_$tmp41, var_$tmp42, var_$tmp43)
                )
                ;
                var_$tmp44 = temp_var_68;
                
                currentMachine.sendEvent(var_$tmp33, var_$tmp34, var_$tmp44);
                
            }
            
        }
        
    }
    
    public static class XrpConservation extends PMonitor {
        
        static State Monitoring = new State("Monitoring", "XrpConservation", StateTemperature.Warm) {
        };
        
        public XrpConservation(int id) {
            super("XrpConservation", id, Monitoring, Monitoring
                
            );
            Monitoring.registerHandlers(
                new EventHandler(ePaymentResp) {
                    @Override public void handleEvent(PMachine machine, PValue<?> payload) {
                        ((XrpConservation)machine).Monitoring_ePaymentResp(machine, (PNamedTuple) payload);
                    }
                });
        }
        
        void 
        Monitoring_ePaymentResp(
            PMachine currentMachine,
            PNamedTuple var_resp
        ) {
            PEnum var_$tmp0 =
                new PEnum("tPaymentStatus", "SUCCESS", 0);
            
            PBool var_$tmp1 =
                new PBool(false);
            
            PInt var_$tmp2 =
                new PInt(0);
            
            PInt var_$tmp3 =
                new PInt(0);
            
            PInt var_$tmp4 =
                new PInt(0);
            
            PInt var_$tmp5 =
                new PInt(0);
            
            PInt var_$tmp6 =
                new PInt(0);
            
            PInt var_$tmp7 =
                new PInt(0);
            
            PBool var_$tmp8 =
                new PBool(false);
            
            PString var_$tmp9 =
                new PString("");
            
            PInt var_$tmp10 =
                new PInt(0);
            
            PInt var_$tmp11 =
                new PInt(0);
            
            PInt var_$tmp12 =
                new PInt(0);
            
            PInt var_$tmp13 =
                new PInt(0);
            
            PString var_$tmp14 =
                new PString("");
            
            PString var_$tmp15 =
                new PString("");
            
            PInt var_$tmp16 =
                new PInt(0);
            
            PInt var_$tmp17 =
                new PInt(0);
            
            PInt var_$tmp18 =
                new PInt(0);
            
            PInt var_$tmp19 =
                new PInt(0);
            
            PInt var_$tmp20 =
                new PInt(0);
            
            PBool var_$tmp21 =
                new PBool(false);
            
            PString var_$tmp22 =
                new PString("");
            
            PInt var_$tmp23 =
                new PInt(0);
            
            PInt var_$tmp24 =
                new PInt(0);
            
            PInt var_$tmp25 =
                new PInt(0);
            
            PString var_$tmp26 =
                new PString("");
            
            PString var_$tmp27 =
                new PString("");
            
            PInt var_$tmp28 =
                new PInt(0);
            
            PInt var_$tmp29 =
                new PInt(0);
            
            PBool var_$tmp30 =
                new PBool(false);
            
            PString var_$tmp31 =
                new PString("");
            
            PString var_$tmp32 =
                new PString("");
            
            PString var_$tmp33 =
                new PString("");
            
            PInt var_$tmp34 =
                new PInt(0);
            
            PInt var_$tmp35 =
                new PInt(0);
            
            PBool var_$tmp36 =
                new PBool(false);
            
            PString var_$tmp37 =
                new PString("");
            
            PString var_$tmp38 =
                new PString("");
            
            PString var_$tmp39 =
                new PString("");
            
            PEnum temp_var_69;
            temp_var_69 = (PEnum) ((PEnum)(((PNamedTuple)var_resp).getField("status")));
            var_$tmp0 = temp_var_69;
            
            PBool temp_var_70;
            temp_var_70 = (PBool) new PBool(PValue.isEqual(var_$tmp0, new PEnum("tPaymentStatus", "SUCCESS", 0)));
            var_$tmp1 = temp_var_70;
            
            PBool temp_var_71 = var_$tmp1;
            if (temp_var_71.getValue()) {
                // 'then' branch
                PInt temp_var_72;
                temp_var_72 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalAfter")));
                var_$tmp2 = temp_var_72;
                
                PInt temp_var_73;
                temp_var_73 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalBefore")));
                var_$tmp3 = temp_var_73;
                
                PInt temp_var_74;
                temp_var_74 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("amount")));
                var_$tmp4 = temp_var_74;
                
                PInt temp_var_75;
                temp_var_75 = (PInt) (var_$tmp3).sub(var_$tmp4);
                var_$tmp5 = temp_var_75;
                
                PInt temp_var_76;
                temp_var_76 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("fee")));
                var_$tmp6 = temp_var_76;
                
                PInt temp_var_77;
                temp_var_77 = (PInt) (var_$tmp5).sub(var_$tmp6);
                var_$tmp7 = temp_var_77;
                
                PBool temp_var_78;
                temp_var_78 = (PBool) new PBool(PValue.isEqual(var_$tmp2, var_$tmp7));
                var_$tmp8 = temp_var_78;
                
                PBool temp_var_79 = var_$tmp8;
                if (temp_var_79.getValue()) {
                    // 'then' branch
                }
                else {
                    // 'else' branch
                    PString temp_var_80;
                    temp_var_80 = (PString) new PString("PSpec/XrpConservation.p:17:17");
                    var_$tmp9 = temp_var_80;
                    
                    PInt temp_var_81;
                    temp_var_81 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalAfter")));
                    var_$tmp10 = temp_var_81;
                    
                    PInt temp_var_82;
                    temp_var_82 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalBefore")));
                    var_$tmp11 = temp_var_82;
                    
                    PInt temp_var_83;
                    temp_var_83 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("amount")));
                    var_$tmp12 = temp_var_83;
                    
                    PInt temp_var_84;
                    temp_var_84 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("fee")));
                    var_$tmp13 = temp_var_84;
                    
                    PString temp_var_85;
                    temp_var_85 = (PString) new PString("Sender balance wrong: {0} != {1} - {2} - {3}", var_$tmp10, var_$tmp11, var_$tmp12, var_$tmp13);
                    var_$tmp14 = temp_var_85;
                    
                    PString temp_var_86;
                    temp_var_86 = (PString) new PString("{0} {1}", var_$tmp9, var_$tmp14);
                    var_$tmp15 = temp_var_86;
                    
                    Assert.fromModel((var_$tmp8).getValue(), var_$tmp15);
                }
                
                PInt temp_var_87;
                temp_var_87 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalAfter")));
                var_$tmp16 = temp_var_87;
                
                PInt temp_var_88;
                temp_var_88 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalBefore")));
                var_$tmp17 = temp_var_88;
                
                PInt temp_var_89;
                temp_var_89 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("amount")));
                var_$tmp18 = temp_var_89;
                
                PInt temp_var_90;
                temp_var_90 = (PInt) (var_$tmp17).add(var_$tmp18);
                var_$tmp19 = temp_var_90;
                
                PInt temp_var_91;
                temp_var_91 = (PInt) (var_$tmp19).add(new PInt(1));
                var_$tmp20 = temp_var_91;
                
                PBool temp_var_92;
                temp_var_92 = (PBool) new PBool(PValue.isEqual(var_$tmp16, var_$tmp20));
                var_$tmp21 = temp_var_92;
                
                PBool temp_var_93 = var_$tmp21;
                if (temp_var_93.getValue()) {
                    // 'then' branch
                }
                else {
                    // 'else' branch
                    PString temp_var_94;
                    temp_var_94 = (PString) new PString("PSpec/XrpConservation.p:22:17");
                    var_$tmp22 = temp_var_94;
                    
                    PInt temp_var_95;
                    temp_var_95 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalAfter")));
                    var_$tmp23 = temp_var_95;
                    
                    PInt temp_var_96;
                    temp_var_96 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalBefore")));
                    var_$tmp24 = temp_var_96;
                    
                    PInt temp_var_97;
                    temp_var_97 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("amount")));
                    var_$tmp25 = temp_var_97;
                    
                    PString temp_var_98;
                    temp_var_98 = (PString) new PString("Receiver balance wrong: {0} != {1} + {2} + 1", var_$tmp23, var_$tmp24, var_$tmp25);
                    var_$tmp26 = temp_var_98;
                    
                    PString temp_var_99;
                    temp_var_99 = (PString) new PString("{0} {1}", var_$tmp22, var_$tmp26);
                    var_$tmp27 = temp_var_99;
                    
                    Assert.fromModel((var_$tmp21).getValue(), var_$tmp27);
                }
                
            }
            else {
                // 'else' branch
                PInt temp_var_100;
                temp_var_100 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalAfter")));
                var_$tmp28 = temp_var_100;
                
                PInt temp_var_101;
                temp_var_101 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("senderBalBefore")));
                var_$tmp29 = temp_var_101;
                
                PBool temp_var_102;
                temp_var_102 = (PBool) new PBool(PValue.isEqual(var_$tmp28, var_$tmp29));
                var_$tmp30 = temp_var_102;
                
                PBool temp_var_103 = var_$tmp30;
                if (temp_var_103.getValue()) {
                    // 'then' branch
                }
                else {
                    // 'else' branch
                    PString temp_var_104;
                    temp_var_104 = (PString) new PString("PSpec/XrpConservation.p:27:17");
                    var_$tmp31 = temp_var_104;
                    
                    PString temp_var_105;
                    temp_var_105 = (PString) new PString("Sender balance changed on failed payment");
                    var_$tmp32 = temp_var_105;
                    
                    PString temp_var_106;
                    temp_var_106 = (PString) new PString("{0} {1}", var_$tmp31, var_$tmp32);
                    var_$tmp33 = temp_var_106;
                    
                    Assert.fromModel((var_$tmp30).getValue(), var_$tmp33);
                }
                
                PInt temp_var_107;
                temp_var_107 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalAfter")));
                var_$tmp34 = temp_var_107;
                
                PInt temp_var_108;
                temp_var_108 = (PInt) ((PInt)(((PNamedTuple)var_resp).getField("receiverBalBefore")));
                var_$tmp35 = temp_var_108;
                
                PBool temp_var_109;
                temp_var_109 = (PBool) new PBool(PValue.isEqual(var_$tmp34, var_$tmp35));
                var_$tmp36 = temp_var_109;
                
                PBool temp_var_110 = var_$tmp36;
                if (temp_var_110.getValue()) {
                    // 'then' branch
                }
                else {
                    // 'else' branch
                    PString temp_var_111;
                    temp_var_111 = (PString) new PString("PSpec/XrpConservation.p:29:17");
                    var_$tmp37 = temp_var_111;
                    
                    PString temp_var_112;
                    temp_var_112 = (PString) new PString("Receiver balance changed on failed payment");
                    var_$tmp38 = temp_var_112;
                    
                    PString temp_var_113;
                    temp_var_113 = (PString) new PString("{0} {1}", var_$tmp37, var_$tmp38);
                    var_$tmp39 = temp_var_113;
                    
                    Assert.fromModel((var_$tmp36).getValue(), var_$tmp39);
                }
                
            }
            
        }
        
    }
    
    public static class TestDriver extends PMachine {
        
        static State Init = new State("Init", "TestDriver", StateTemperature.Warm) {
            @Generated
            @Override
            public void entry(PMachine machine, PValue<?> payload) {
                super.entry(machine, payload);
                ((TestDriver)machine).Init_entry(machine);
            }
        };
        static State SendPayments = new State("SendPayments", "TestDriver", StateTemperature.Warm) {
            @Generated
            @Override
            public void entry(PMachine machine, PValue<?> payload) {
                super.entry(machine, payload);
                ((TestDriver)machine).SendPayments_entry(machine);
            }
        };
        public PMachineValue var_engine = null;
        public PInt var_paymentsLeft = new PInt(0);
        
        public TestDriver(int id) {
            super("TestDriver", id, Init, Init
                , SendPayments
                
            );
            Init.registerHandlers(
            );
            SendPayments.registerHandlers(
                new EventHandler(ePaymentResp) {
                    @Override public void handleEvent(PMachine machine, PValue<?> payload) {
                        ((TestDriver)machine).SendPayments_ePaymentResp(machine);
                    }
                });
        }
        
        void 
        Init_entry(
            PMachine currentMachine
        ) {
            PMap var_balances =
                new PMap();
            
            PMap var_$tmp0 =
                new PMap();
            
            PInt var_$tmp1 =
                new PInt(0);
            
            PInt var_$tmp2 =
                new PInt(0);
            
            PMachineValue var_$tmp3 =
                null;
            
            PNamedTuple var_$tmp4 =
                new PNamedTuple(List.of("balances", "fee", "reserve", "client"), Arrays.asList(new PMap(), new PInt(0), new PInt(0), null));
            
            PMachineValue var_$tmp5 =
                null;
            
            PMap temp_var_114 = (PMap) var_balances;    
            PInt temp_var_116 = new PInt(0);
            PInt temp_var_115;
            temp_var_115 = (PInt) new PInt(10000);
            temp_var_114 = temp_var_114.put(temp_var_116, temp_var_115);
            var_balances = temp_var_114;
            
            PMap temp_var_117 = (PMap) var_balances;    
            PInt temp_var_119 = new PInt(1);
            PInt temp_var_118;
            temp_var_118 = (PInt) new PInt(5000);
            temp_var_117 = temp_var_117.put(temp_var_119, temp_var_118);
            var_balances = temp_var_117;
            
            PMap temp_var_120;
            temp_var_120 = (PMap) var_balances;
            var_$tmp0 = temp_var_120;
            
            PInt temp_var_121;
            temp_var_121 = (PInt) new PInt(12);
            var_$tmp1 = temp_var_121;
            
            PInt temp_var_122;
            temp_var_122 = (PInt) new PInt(200);
            var_$tmp2 = temp_var_122;
            
            PMachineValue temp_var_123;
            temp_var_123 = (PMachineValue) new PMachineValue(this);
            var_$tmp3 = temp_var_123;
            
            PNamedTuple temp_var_124;
            temp_var_124 = (PNamedTuple) new PNamedTuple(
                List.of("balances", "fee", "reserve", "client"), 
                Arrays.asList(var_$tmp0, var_$tmp1, var_$tmp2, var_$tmp3)
            )
            ;
            var_$tmp4 = temp_var_124;
            
            PMachineValue temp_var_125;
            temp_var_125 = (PMachineValue) currentMachine.create(PaymentEngine.class, var_$tmp4);
            var_$tmp5 = temp_var_125;
            
            PMachineValue temp_var_126;
            temp_var_126 = (PMachineValue) var_$tmp5;
            var_engine = temp_var_126;
            
            PInt temp_var_127;
            temp_var_127 = (PInt) new PInt(3);
            var_paymentsLeft = temp_var_127;
            
            currentMachine.gotoState(SendPayments, null);
            return;
            
        }
        
        void 
        SendPayments_entry(
            PMachine currentMachine
        ) {
            PBool var_$tmp0 =
                new PBool(false);
            
            PBool var_$tmp1 =
                new PBool(false);
            
            PMachineValue var_$tmp2 =
                null;
            
            Event var_$tmp3 =
                null;
            
            PInt var_$tmp4 =
                new PInt(0);
            
            PInt var_$tmp5 =
                new PInt(0);
            
            PInt var_$tmp6 =
                new PInt(0);
            
            PNamedTuple var_$tmp7 =
                new PNamedTuple(List.of("sender", "receiver", "amount"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0)));
            
            PBool var_$tmp8 =
                new PBool(false);
            
            PMachineValue var_$tmp9 =
                null;
            
            Event var_$tmp10 =
                null;
            
            PInt var_$tmp11 =
                new PInt(0);
            
            PInt var_$tmp12 =
                new PInt(0);
            
            PInt var_$tmp13 =
                new PInt(0);
            
            PNamedTuple var_$tmp14 =
                new PNamedTuple(List.of("sender", "receiver", "amount"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0)));
            
            PBool var_$tmp15 =
                new PBool(false);
            
            PMachineValue var_$tmp16 =
                null;
            
            Event var_$tmp17 =
                null;
            
            PInt var_$tmp18 =
                new PInt(0);
            
            PInt var_$tmp19 =
                new PInt(0);
            
            PInt var_$tmp20 =
                new PInt(0);
            
            PNamedTuple var_$tmp21 =
                new PNamedTuple(List.of("sender", "receiver", "amount"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0)));
            
            PMachineValue var_$tmp22 =
                null;
            
            Event var_$tmp23 =
                null;
            
            PInt var_$tmp24 =
                new PInt(0);
            
            PInt var_$tmp25 =
                new PInt(0);
            
            PInt var_$tmp26 =
                new PInt(0);
            
            PNamedTuple var_$tmp27 =
                new PNamedTuple(List.of("sender", "receiver", "amount"), Arrays.asList(new PInt(0), new PInt(0), new PInt(0)));
            
            PInt var_$tmp28 =
                new PInt(0);
            
            PBool temp_var_128;
            temp_var_128 = (PBool) (var_paymentsLeft).gt(new PInt(0));
            var_$tmp0 = temp_var_128;
            
            PBool temp_var_129 = var_$tmp0;
            if (temp_var_129.getValue()) {
                // 'then' branch
                PBool temp_var_130;
                temp_var_130 = (PBool) PExGlobal.getScheduler().getRandomBool("PTst/TestDriver.p:33:21");
                var_$tmp1 = temp_var_130;
                
                PBool temp_var_131 = var_$tmp1;
                if (temp_var_131.getValue()) {
                    // 'then' branch
                    PMachineValue temp_var_132;
                    temp_var_132 = (PMachineValue) var_engine;
                    var_$tmp2 = temp_var_132;
                    
                    Event temp_var_133;
                    temp_var_133 = (Event) new Event(ePaymentReq);
                    var_$tmp3 = temp_var_133;
                    
                    PInt temp_var_134;
                    temp_var_134 = (PInt) new PInt(0);
                    var_$tmp4 = temp_var_134;
                    
                    PInt temp_var_135;
                    temp_var_135 = (PInt) new PInt(1);
                    var_$tmp5 = temp_var_135;
                    
                    PInt temp_var_136;
                    temp_var_136 = (PInt) new PInt(500);
                    var_$tmp6 = temp_var_136;
                    
                    PNamedTuple temp_var_137;
                    temp_var_137 = (PNamedTuple) new PNamedTuple(
                        List.of("sender", "receiver", "amount"), 
                        Arrays.asList(var_$tmp4, var_$tmp5, var_$tmp6)
                    )
                    ;
                    var_$tmp7 = temp_var_137;
                    
                    currentMachine.sendEvent(var_$tmp2, var_$tmp3, var_$tmp7);
                    
                }
                else {
                    // 'else' branch
                    PBool temp_var_138;
                    temp_var_138 = (PBool) PExGlobal.getScheduler().getRandomBool("PTst/TestDriver.p:37:28");
                    var_$tmp8 = temp_var_138;
                    
                    PBool temp_var_139 = var_$tmp8;
                    if (temp_var_139.getValue()) {
                        // 'then' branch
                        PMachineValue temp_var_140;
                        temp_var_140 = (PMachineValue) var_engine;
                        var_$tmp9 = temp_var_140;
                        
                        Event temp_var_141;
                        temp_var_141 = (Event) new Event(ePaymentReq);
                        var_$tmp10 = temp_var_141;
                        
                        PInt temp_var_142;
                        temp_var_142 = (PInt) new PInt(0);
                        var_$tmp11 = temp_var_142;
                        
                        PInt temp_var_143;
                        temp_var_143 = (PInt) new PInt(1);
                        var_$tmp12 = temp_var_143;
                        
                        PInt temp_var_144;
                        temp_var_144 = (PInt) new PInt(9000);
                        var_$tmp13 = temp_var_144;
                        
                        PNamedTuple temp_var_145;
                        temp_var_145 = (PNamedTuple) new PNamedTuple(
                            List.of("sender", "receiver", "amount"), 
                            Arrays.asList(var_$tmp11, var_$tmp12, var_$tmp13)
                        )
                        ;
                        var_$tmp14 = temp_var_145;
                        
                        currentMachine.sendEvent(var_$tmp9, var_$tmp10, var_$tmp14);
                        
                    }
                    else {
                        // 'else' branch
                        PBool temp_var_146;
                        temp_var_146 = (PBool) PExGlobal.getScheduler().getRandomBool("PTst/TestDriver.p:41:28");
                        var_$tmp15 = temp_var_146;
                        
                        PBool temp_var_147 = var_$tmp15;
                        if (temp_var_147.getValue()) {
                            // 'then' branch
                            PMachineValue temp_var_148;
                            temp_var_148 = (PMachineValue) var_engine;
                            var_$tmp16 = temp_var_148;
                            
                            Event temp_var_149;
                            temp_var_149 = (Event) new Event(ePaymentReq);
                            var_$tmp17 = temp_var_149;
                            
                            PInt temp_var_150;
                            temp_var_150 = (PInt) new PInt(1);
                            var_$tmp18 = temp_var_150;
                            
                            PInt temp_var_151;
                            temp_var_151 = (PInt) new PInt(0);
                            var_$tmp19 = temp_var_151;
                            
                            PInt temp_var_152;
                            temp_var_152 = (PInt) new PInt(1000);
                            var_$tmp20 = temp_var_152;
                            
                            PNamedTuple temp_var_153;
                            temp_var_153 = (PNamedTuple) new PNamedTuple(
                                List.of("sender", "receiver", "amount"), 
                                Arrays.asList(var_$tmp18, var_$tmp19, var_$tmp20)
                            )
                            ;
                            var_$tmp21 = temp_var_153;
                            
                            currentMachine.sendEvent(var_$tmp16, var_$tmp17, var_$tmp21);
                            
                        }
                        else {
                            // 'else' branch
                            PMachineValue temp_var_154;
                            temp_var_154 = (PMachineValue) var_engine;
                            var_$tmp22 = temp_var_154;
                            
                            Event temp_var_155;
                            temp_var_155 = (Event) new Event(ePaymentReq);
                            var_$tmp23 = temp_var_155;
                            
                            PInt temp_var_156;
                            temp_var_156 = (PInt) new PInt(0);
                            var_$tmp24 = temp_var_156;
                            
                            PInt temp_var_157;
                            temp_var_157 = (PInt) new PInt(1);
                            var_$tmp25 = temp_var_157;
                            
                            PInt temp_var_158;
                            temp_var_158 = (PInt) new PInt(100000);
                            var_$tmp26 = temp_var_158;
                            
                            PNamedTuple temp_var_159;
                            temp_var_159 = (PNamedTuple) new PNamedTuple(
                                List.of("sender", "receiver", "amount"), 
                                Arrays.asList(var_$tmp24, var_$tmp25, var_$tmp26)
                            )
                            ;
                            var_$tmp27 = temp_var_159;
                            
                            currentMachine.sendEvent(var_$tmp22, var_$tmp23, var_$tmp27);
                            
                        }
                        
                    }
                    
                }
                
                PInt temp_var_160;
                temp_var_160 = (PInt) (var_paymentsLeft).sub(new PInt(1));
                var_$tmp28 = temp_var_160;
                
                PInt temp_var_161;
                temp_var_161 = (PInt) var_$tmp28;
                var_paymentsLeft = temp_var_161;
                
            }
            else {
                // 'else' branch
            }
            
        }
        
        void 
        SendPayments_ePaymentResp(
            PMachine currentMachine
        ) {
            currentMachine.gotoState(SendPayments, null);
            return;
            
        }
        
    }
    
    // Skipping TypeDef 'tAccountId'

    // Skipping TypeDef 'tPaymentReq'

    // Skipping TypeDef 'tPaymentResp'

    @Generated
    public static class test_testXrpPayment extends PTestDriver {
        public static void InitializeGlobalParams() {
        }
        
        @Generated
        public void configure() {
                InitializeGlobalParams();
                mainMachine = TestDriver.class;
            
                interfaceMap.clear();
                interfaceMap.put(TestDriver.class, TestDriver.class);
                interfaceMap.put(PaymentEngine.class, PaymentEngine.class);
            
                monitorList.clear();
                observerMap.clear();
            
                Class<? extends PMachine> cls_XrpConservation = XrpConservation.class;
                monitorList.add(cls_XrpConservation);
                if(!observerMap.containsKey(ePaymentResp))
                    observerMap.put(ePaymentResp, new ArrayList<>());
                observerMap.get(ePaymentResp).add(cls_XrpConservation);
        }
        
    }
    
    PTestDriver testDriver = null;
    @Generated
    public PTestDriver getTestDriver() { return testDriver; }
    @Generated
    public void setTestDriver(PTestDriver input) { testDriver = input; }
    
}
