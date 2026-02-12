using PChecker;
using PChecker.Runtime;
using PChecker.Runtime.StateMachines;
using PChecker.Runtime.Events;
using PChecker.Runtime.Exceptions;
using PChecker.Runtime.Logging;
using PChecker.Runtime.Values;
using PChecker.Runtime.Specifications;
using Monitor = PChecker.Runtime.Specifications.Monitor;
using System;
using PChecker.SystematicTesting;
using System.Runtime;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Threading;
using System.Threading.Tasks;

#pragma warning disable 162, 219, 414, 1998
namespace PImplementation
{
}
namespace PImplementation
{
    public static class GlobalConfig
    {
    }
}
namespace PImplementation
{
    internal partial class ePaymentReq : Event
    {
        public ePaymentReq() : base() {}
        public ePaymentReq (PNamedTuple payload): base(payload){ }
        public override IPValue Clone() { return new ePaymentReq();}
    }
}
namespace PImplementation
{
    internal partial class ePaymentResp : Event
    {
        public ePaymentResp() : base() {}
        public ePaymentResp (PNamedTuple payload): base(payload){ }
        public override IPValue Clone() { return new ePaymentResp();}
    }
}
namespace PImplementation
{
    internal partial class PaymentEngine : StateMachine
    {
        private PMap balances = new PMap();
        private PInt fee = ((PInt)0);
        private PInt reserve = ((PInt)0);
        private PMachineValue client = null;
        public class ConstructorEvent : Event{public ConstructorEvent(IPValue val) : base(val) { }}
        
        protected override Event GetConstructorEvent(IPValue value) { return new ConstructorEvent((IPValue)value); }
        public PaymentEngine() {
            this.sends.Add(nameof(ePaymentReq));
            this.sends.Add(nameof(ePaymentResp));
            this.sends.Add(nameof(PHalt));
            this.receives.Add(nameof(ePaymentReq));
            this.receives.Add(nameof(ePaymentResp));
            this.receives.Add(nameof(PHalt));
        }
        
        public void Anon(Event currentMachine_dequeuedEvent)
        {
            PaymentEngine currentMachine = this;
            PNamedTuple config = (PNamedTuple)(gotoPayload ?? ((Event)currentMachine_dequeuedEvent).Payload);
            this.gotoPayload = null;
            PMap TMP_tmp0 = new PMap();
            PMap TMP_tmp1 = new PMap();
            PInt TMP_tmp2 = ((PInt)0);
            PInt TMP_tmp3 = ((PInt)0);
            PInt TMP_tmp4 = ((PInt)0);
            PInt TMP_tmp5 = ((PInt)0);
            PMachineValue TMP_tmp6 = null;
            PMachineValue TMP_tmp7 = null;
            TMP_tmp0 = (PMap)(((PNamedTuple)config)["balances"]);
            TMP_tmp1 = (PMap)(((PMap)((IPValue)TMP_tmp0)?.Clone()));
            balances = TMP_tmp1;
            TMP_tmp2 = (PInt)(((PNamedTuple)config)["fee"]);
            TMP_tmp3 = (PInt)(((PInt)((IPValue)TMP_tmp2)?.Clone()));
            fee = TMP_tmp3;
            TMP_tmp4 = (PInt)(((PNamedTuple)config)["reserve"]);
            TMP_tmp5 = (PInt)(((PInt)((IPValue)TMP_tmp4)?.Clone()));
            reserve = TMP_tmp5;
            TMP_tmp6 = (PMachineValue)(((PNamedTuple)config)["client"]);
            TMP_tmp7 = (PMachineValue)(((PMachineValue)((IPValue)TMP_tmp6)?.Clone()));
            client = TMP_tmp7;
            currentMachine.RaiseGotoStateEvent<Ready>();
            return;
        }
        public void Anon_1(Event currentMachine_dequeuedEvent)
        {
            PaymentEngine currentMachine = this;
            PNamedTuple req = (PNamedTuple)(gotoPayload ?? ((Event)currentMachine_dequeuedEvent).Payload);
            this.gotoPayload = null;
            PInt senderBal = ((PInt)0);
            PInt receiverBal = ((PInt)0);
            PInt minRequired = ((PInt)0);
            PInt TMP_tmp0_1 = ((PInt)0);
            PInt TMP_tmp1_1 = ((PInt)0);
            PInt TMP_tmp2_1 = ((PInt)0);
            PInt TMP_tmp3_1 = ((PInt)0);
            PInt TMP_tmp4_1 = ((PInt)0);
            PInt TMP_tmp5_1 = ((PInt)0);
            PBool TMP_tmp6_1 = ((PBool)false);
            PInt TMP_tmp7_1 = ((PInt)0);
            PInt TMP_tmp8 = ((PInt)0);
            PInt TMP_tmp9 = ((PInt)0);
            PInt TMP_tmp10 = ((PInt)0);
            PBool TMP_tmp11 = ((PBool)false);
            PInt TMP_tmp12 = ((PInt)0);
            PInt TMP_tmp13 = ((PInt)0);
            PInt TMP_tmp14 = ((PInt)0);
            PInt TMP_tmp15 = ((PInt)0);
            PInt TMP_tmp16 = ((PInt)0);
            PInt TMP_tmp17 = ((PInt)0);
            PInt TMP_tmp18 = ((PInt)0);
            PMachineValue TMP_tmp19 = null;
            Event TMP_tmp20 = null;
            PInt TMP_tmp21 = ((PInt)0);
            PInt TMP_tmp22 = ((PInt)0);
            PInt TMP_tmp23 = ((PInt)0);
            PInt TMP_tmp24 = ((PInt)0);
            PInt TMP_tmp25 = ((PInt)0);
            PInt TMP_tmp26 = ((PInt)0);
            PInt TMP_tmp27 = ((PInt)0);
            PInt TMP_tmp28 = ((PInt)0);
            PInt TMP_tmp29 = ((PInt)0);
            PInt TMP_tmp30 = ((PInt)0);
            PInt TMP_tmp31 = ((PInt)0);
            PNamedTuple TMP_tmp32 = (new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"},((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0)));
            PMachineValue TMP_tmp33 = null;
            Event TMP_tmp34 = null;
            PInt TMP_tmp35 = ((PInt)0);
            PInt TMP_tmp36 = ((PInt)0);
            PInt TMP_tmp37 = ((PInt)0);
            PInt TMP_tmp38 = ((PInt)0);
            PInt TMP_tmp39 = ((PInt)0);
            PInt TMP_tmp40 = ((PInt)0);
            PInt TMP_tmp41 = ((PInt)0);
            PInt TMP_tmp42 = ((PInt)0);
            PInt TMP_tmp43 = ((PInt)0);
            PNamedTuple TMP_tmp44 = (new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"},((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0)));
            TMP_tmp0_1 = (PInt)(((PNamedTuple)req)["sender"]);
            TMP_tmp1_1 = (PInt)(((PMap)balances)[TMP_tmp0_1]);
            TMP_tmp2_1 = (PInt)(((PInt)((IPValue)TMP_tmp1_1)?.Clone()));
            senderBal = TMP_tmp2_1;
            TMP_tmp3_1 = (PInt)(((PNamedTuple)req)["receiver"]);
            TMP_tmp4_1 = (PInt)(((PMap)balances)[TMP_tmp3_1]);
            TMP_tmp5_1 = (PInt)(((PInt)((IPValue)TMP_tmp4_1)?.Clone()));
            receiverBal = TMP_tmp5_1;
            TMP_tmp6_1 = (PBool)((reserve) > (fee));
            if (TMP_tmp6_1)
            {
                TMP_tmp7_1 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp8 = (PInt)((TMP_tmp7_1) + (reserve));
                minRequired = TMP_tmp8;
            }
            else
            {
                TMP_tmp9 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp10 = (PInt)((TMP_tmp9) + (fee));
                minRequired = TMP_tmp10;
            }
            TMP_tmp11 = (PBool)((senderBal) >= (minRequired));
            if (TMP_tmp11)
            {
                TMP_tmp12 = (PInt)(((PNamedTuple)req)["sender"]);
                TMP_tmp13 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp14 = (PInt)((senderBal) - (TMP_tmp13));
                TMP_tmp15 = (PInt)((TMP_tmp14) - (fee));
                ((PMap)balances)[TMP_tmp12] = TMP_tmp15;
                TMP_tmp16 = (PInt)(((PNamedTuple)req)["receiver"]);
                TMP_tmp17 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp18 = (PInt)((receiverBal) + (TMP_tmp17));
                ((PMap)balances)[TMP_tmp16] = TMP_tmp18;
                TMP_tmp19 = (PMachineValue)(((PMachineValue)((IPValue)client)?.Clone()));
                TMP_tmp20 = (Event)(new ePaymentResp((new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"},((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0)))));
                TMP_tmp21 = (PInt)(((PNamedTuple)req)["sender"]);
                TMP_tmp22 = (PInt)(((PNamedTuple)req)["receiver"]);
                TMP_tmp23 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp24 = (PInt)(((PInt)((IPValue)fee)?.Clone()));
                TMP_tmp25 = (PInt)((PEnum.Get("SUCCESS")));
                TMP_tmp26 = (PInt)(((PInt)((IPValue)senderBal)?.Clone()));
                TMP_tmp27 = (PInt)(((PNamedTuple)req)["sender"]);
                TMP_tmp28 = (PInt)(((PMap)balances)[TMP_tmp27]);
                TMP_tmp29 = (PInt)(((PInt)((IPValue)receiverBal)?.Clone()));
                TMP_tmp30 = (PInt)(((PNamedTuple)req)["receiver"]);
                TMP_tmp31 = (PInt)(((PMap)balances)[TMP_tmp30]);
                TMP_tmp32 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"}, TMP_tmp21, TMP_tmp22, TMP_tmp23, TMP_tmp24, TMP_tmp25, TMP_tmp26, TMP_tmp28, TMP_tmp29, TMP_tmp31)));
                TMP_tmp20.Payload = TMP_tmp32;
                currentMachine.SendEvent(TMP_tmp19, (Event)TMP_tmp20);
            }
            else
            {
                TMP_tmp33 = (PMachineValue)(((PMachineValue)((IPValue)client)?.Clone()));
                TMP_tmp34 = (Event)(new ePaymentResp((new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"},((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0), ((PInt)0)))));
                TMP_tmp35 = (PInt)(((PNamedTuple)req)["sender"]);
                TMP_tmp36 = (PInt)(((PNamedTuple)req)["receiver"]);
                TMP_tmp37 = (PInt)(((PNamedTuple)req)["amount"]);
                TMP_tmp38 = (PInt)(((PInt)((IPValue)fee)?.Clone()));
                TMP_tmp39 = (PInt)((PEnum.Get("INSUFFICIENT_FUNDS")));
                TMP_tmp40 = (PInt)(((PInt)((IPValue)senderBal)?.Clone()));
                TMP_tmp41 = (PInt)(((PInt)((IPValue)senderBal)?.Clone()));
                TMP_tmp42 = (PInt)(((PInt)((IPValue)receiverBal)?.Clone()));
                TMP_tmp43 = (PInt)(((PInt)((IPValue)receiverBal)?.Clone()));
                TMP_tmp44 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount","fee","status","senderBalBefore","senderBalAfter","receiverBalBefore","receiverBalAfter"}, TMP_tmp35, TMP_tmp36, TMP_tmp37, TMP_tmp38, TMP_tmp39, TMP_tmp40, TMP_tmp41, TMP_tmp42, TMP_tmp43)));
                TMP_tmp34.Payload = TMP_tmp44;
                currentMachine.SendEvent(TMP_tmp33, (Event)TMP_tmp34);
            }
        }
        [Start]
        [OnEntry(nameof(Anon))]
        class Init : State
        {
        }
        [OnEventDoAction(typeof(ePaymentReq), nameof(Anon_1))]
        class Ready : State
        {
        }
    }
}
namespace PImplementation
{
    internal partial class XrpConservation : Monitor
    {
        static XrpConservation() {
            observes.Add(nameof(ePaymentResp));
        }
        
        public void Anon_2(Event currentMachine_dequeuedEvent)
        {
            XrpConservation currentMachine = this;
            PNamedTuple resp = (PNamedTuple)(gotoPayload ?? ((Event)currentMachine_dequeuedEvent).Payload);
            this.gotoPayload = null;
            PInt TMP_tmp0_2 = ((PInt)0);
            PBool TMP_tmp1_2 = ((PBool)false);
            PInt TMP_tmp2_2 = ((PInt)0);
            PInt TMP_tmp3_2 = ((PInt)0);
            PInt TMP_tmp4_2 = ((PInt)0);
            PInt TMP_tmp5_2 = ((PInt)0);
            PInt TMP_tmp6_2 = ((PInt)0);
            PInt TMP_tmp7_2 = ((PInt)0);
            PBool TMP_tmp8_1 = ((PBool)false);
            PString TMP_tmp9_1 = ((PString)"");
            PInt TMP_tmp10_1 = ((PInt)0);
            PInt TMP_tmp11_1 = ((PInt)0);
            PInt TMP_tmp12_1 = ((PInt)0);
            PInt TMP_tmp13_1 = ((PInt)0);
            PString TMP_tmp14_1 = ((PString)"");
            PString TMP_tmp15_1 = ((PString)"");
            PInt TMP_tmp16_1 = ((PInt)0);
            PInt TMP_tmp17_1 = ((PInt)0);
            PInt TMP_tmp18_1 = ((PInt)0);
            PInt TMP_tmp19_1 = ((PInt)0);
            PBool TMP_tmp20_1 = ((PBool)false);
            PString TMP_tmp21_1 = ((PString)"");
            PInt TMP_tmp22_1 = ((PInt)0);
            PInt TMP_tmp23_1 = ((PInt)0);
            PInt TMP_tmp24_1 = ((PInt)0);
            PString TMP_tmp25_1 = ((PString)"");
            PString TMP_tmp26_1 = ((PString)"");
            PInt TMP_tmp27_1 = ((PInt)0);
            PInt TMP_tmp28_1 = ((PInt)0);
            PBool TMP_tmp29_1 = ((PBool)false);
            PString TMP_tmp30_1 = ((PString)"");
            PString TMP_tmp31_1 = ((PString)"");
            PString TMP_tmp32_1 = ((PString)"");
            PInt TMP_tmp33_1 = ((PInt)0);
            PInt TMP_tmp34_1 = ((PInt)0);
            PBool TMP_tmp35_1 = ((PBool)false);
            PString TMP_tmp36_1 = ((PString)"");
            PString TMP_tmp37_1 = ((PString)"");
            PString TMP_tmp38_1 = ((PString)"");
            TMP_tmp0_2 = (PInt)(((PNamedTuple)resp)["status"]);
            TMP_tmp1_2 = (PBool)((PValues.SafeEquals(PValues.Box((long) TMP_tmp0_2),PValues.Box((long) (PEnum.Get("SUCCESS"))))));
            if (TMP_tmp1_2)
            {
                TMP_tmp2_2 = (PInt)(((PNamedTuple)resp)["senderBalAfter"]);
                TMP_tmp3_2 = (PInt)(((PNamedTuple)resp)["senderBalBefore"]);
                TMP_tmp4_2 = (PInt)(((PNamedTuple)resp)["amount"]);
                TMP_tmp5_2 = (PInt)((TMP_tmp3_2) - (TMP_tmp4_2));
                TMP_tmp6_2 = (PInt)(((PNamedTuple)resp)["fee"]);
                TMP_tmp7_2 = (PInt)((TMP_tmp5_2) - (TMP_tmp6_2));
                TMP_tmp8_1 = (PBool)((PValues.SafeEquals(TMP_tmp2_2,TMP_tmp7_2)));
                if (TMP_tmp8_1)
                {
                }
                else
                {
                    TMP_tmp9_1 = (PString)(((PString) String.Format("PSpec/XrpConservation.p:17:17")));
                    TMP_tmp10_1 = (PInt)(((PNamedTuple)resp)["senderBalAfter"]);
                    TMP_tmp11_1 = (PInt)(((PNamedTuple)resp)["senderBalBefore"]);
                    TMP_tmp12_1 = (PInt)(((PNamedTuple)resp)["amount"]);
                    TMP_tmp13_1 = (PInt)(((PNamedTuple)resp)["fee"]);
                    TMP_tmp14_1 = (PString)(((PString) String.Format("Sender balance wrong: {0} != {1} - {2} - {3}",TMP_tmp10_1,TMP_tmp11_1,TMP_tmp12_1,TMP_tmp13_1)));
                    TMP_tmp15_1 = (PString)(((PString) String.Format("{0} {1}",TMP_tmp9_1,TMP_tmp14_1)));
                    currentMachine.Assert(TMP_tmp8_1,"Assertion Failed: " + TMP_tmp15_1);
                }
                TMP_tmp16_1 = (PInt)(((PNamedTuple)resp)["receiverBalAfter"]);
                TMP_tmp17_1 = (PInt)(((PNamedTuple)resp)["receiverBalBefore"]);
                TMP_tmp18_1 = (PInt)(((PNamedTuple)resp)["amount"]);
                TMP_tmp19_1 = (PInt)((TMP_tmp17_1) + (TMP_tmp18_1));
                TMP_tmp20_1 = (PBool)((PValues.SafeEquals(TMP_tmp16_1,TMP_tmp19_1)));
                if (TMP_tmp20_1)
                {
                }
                else
                {
                    TMP_tmp21_1 = (PString)(((PString) String.Format("PSpec/XrpConservation.p:22:17")));
                    TMP_tmp22_1 = (PInt)(((PNamedTuple)resp)["receiverBalAfter"]);
                    TMP_tmp23_1 = (PInt)(((PNamedTuple)resp)["receiverBalBefore"]);
                    TMP_tmp24_1 = (PInt)(((PNamedTuple)resp)["amount"]);
                    TMP_tmp25_1 = (PString)(((PString) String.Format("Receiver balance wrong: {0} != {1} + {2}",TMP_tmp22_1,TMP_tmp23_1,TMP_tmp24_1)));
                    TMP_tmp26_1 = (PString)(((PString) String.Format("{0} {1}",TMP_tmp21_1,TMP_tmp25_1)));
                    currentMachine.Assert(TMP_tmp20_1,"Assertion Failed: " + TMP_tmp26_1);
                }
            }
            else
            {
                TMP_tmp27_1 = (PInt)(((PNamedTuple)resp)["senderBalAfter"]);
                TMP_tmp28_1 = (PInt)(((PNamedTuple)resp)["senderBalBefore"]);
                TMP_tmp29_1 = (PBool)((PValues.SafeEquals(TMP_tmp27_1,TMP_tmp28_1)));
                if (TMP_tmp29_1)
                {
                }
                else
                {
                    TMP_tmp30_1 = (PString)(((PString) String.Format("PSpec/XrpConservation.p:27:17")));
                    TMP_tmp31_1 = (PString)(((PString) String.Format("Sender balance changed on failed payment")));
                    TMP_tmp32_1 = (PString)(((PString) String.Format("{0} {1}",TMP_tmp30_1,TMP_tmp31_1)));
                    currentMachine.Assert(TMP_tmp29_1,"Assertion Failed: " + TMP_tmp32_1);
                }
                TMP_tmp33_1 = (PInt)(((PNamedTuple)resp)["receiverBalAfter"]);
                TMP_tmp34_1 = (PInt)(((PNamedTuple)resp)["receiverBalBefore"]);
                TMP_tmp35_1 = (PBool)((PValues.SafeEquals(TMP_tmp33_1,TMP_tmp34_1)));
                if (TMP_tmp35_1)
                {
                }
                else
                {
                    TMP_tmp36_1 = (PString)(((PString) String.Format("PSpec/XrpConservation.p:29:17")));
                    TMP_tmp37_1 = (PString)(((PString) String.Format("Receiver balance changed on failed payment")));
                    TMP_tmp38_1 = (PString)(((PString) String.Format("{0} {1}",TMP_tmp36_1,TMP_tmp37_1)));
                    currentMachine.Assert(TMP_tmp35_1,"Assertion Failed: " + TMP_tmp38_1);
                }
            }
        }
        [Start]
        [OnEventDoAction(typeof(ePaymentResp), nameof(Anon_2))]
        class Monitoring : State
        {
        }
    }
}
namespace PImplementation
{
    internal partial class TestDriver : StateMachine
    {
        private PMachineValue engine = null;
        private PInt paymentsLeft = ((PInt)0);
        public class ConstructorEvent : Event{public ConstructorEvent(IPValue val) : base(val) { }}
        
        protected override Event GetConstructorEvent(IPValue value) { return new ConstructorEvent((IPValue)value); }
        public TestDriver() {
            this.sends.Add(nameof(ePaymentReq));
            this.sends.Add(nameof(ePaymentResp));
            this.sends.Add(nameof(PHalt));
            this.receives.Add(nameof(ePaymentReq));
            this.receives.Add(nameof(ePaymentResp));
            this.receives.Add(nameof(PHalt));
            this.creates.Add(nameof(I_PaymentEngine));
        }
        
        public void Anon_3(Event currentMachine_dequeuedEvent)
        {
            TestDriver currentMachine = this;
            PMap balances_1 = new PMap();
            PMap TMP_tmp0_3 = new PMap();
            PInt TMP_tmp1_3 = ((PInt)0);
            PInt TMP_tmp2_3 = ((PInt)0);
            PMachineValue TMP_tmp3_3 = null;
            PNamedTuple TMP_tmp4_3 = (new PNamedTuple(new string[]{"balances","fee","reserve","client"},new PMap(), ((PInt)0), ((PInt)0), null));
            PMachineValue TMP_tmp5_3 = null;
            ((PMap)balances_1)[((PInt)(0))] = (PInt)(((PInt)(10000)));
            ((PMap)balances_1)[((PInt)(1))] = (PInt)(((PInt)(5000)));
            TMP_tmp0_3 = (PMap)(((PMap)((IPValue)balances_1)?.Clone()));
            TMP_tmp1_3 = (PInt)(((PInt)(12)));
            TMP_tmp2_3 = (PInt)(((PInt)(200)));
            TMP_tmp3_3 = (PMachineValue)(currentMachine.self);
            TMP_tmp4_3 = (PNamedTuple)((new PNamedTuple(new string[]{"balances","fee","reserve","client"}, TMP_tmp0_3, TMP_tmp1_3, TMP_tmp2_3, TMP_tmp3_3)));
            TMP_tmp5_3 = (PMachineValue)(currentMachine.CreateInterface<I_PaymentEngine>( currentMachine, TMP_tmp4_3));
            engine = (PMachineValue)TMP_tmp5_3;
            paymentsLeft = (PInt)(((PInt)(3)));
            currentMachine.RaiseGotoStateEvent<SendPayments>();
            return;
        }
        public void Anon_4(Event currentMachine_dequeuedEvent)
        {
            TestDriver currentMachine = this;
            PBool TMP_tmp0_4 = ((PBool)false);
            PBool TMP_tmp1_4 = ((PBool)false);
            PMachineValue TMP_tmp2_4 = null;
            Event TMP_tmp3_4 = null;
            PInt TMP_tmp4_4 = ((PInt)0);
            PInt TMP_tmp5_4 = ((PInt)0);
            PInt TMP_tmp6_3 = ((PInt)0);
            PNamedTuple TMP_tmp7_3 = (new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)));
            PBool TMP_tmp8_2 = ((PBool)false);
            PMachineValue TMP_tmp9_2 = null;
            Event TMP_tmp10_2 = null;
            PInt TMP_tmp11_2 = ((PInt)0);
            PInt TMP_tmp12_2 = ((PInt)0);
            PInt TMP_tmp13_2 = ((PInt)0);
            PNamedTuple TMP_tmp14_2 = (new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)));
            PBool TMP_tmp15_2 = ((PBool)false);
            PMachineValue TMP_tmp16_2 = null;
            Event TMP_tmp17_2 = null;
            PInt TMP_tmp18_2 = ((PInt)0);
            PInt TMP_tmp19_2 = ((PInt)0);
            PInt TMP_tmp20_2 = ((PInt)0);
            PNamedTuple TMP_tmp21_2 = (new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)));
            PMachineValue TMP_tmp22_2 = null;
            Event TMP_tmp23_2 = null;
            PInt TMP_tmp24_2 = ((PInt)0);
            PInt TMP_tmp25_2 = ((PInt)0);
            PInt TMP_tmp26_2 = ((PInt)0);
            PNamedTuple TMP_tmp27_2 = (new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)));
            PInt TMP_tmp28_2 = ((PInt)0);
            TMP_tmp0_4 = (PBool)((paymentsLeft) > (((PInt)(0))));
            if (TMP_tmp0_4)
            {
                TMP_tmp1_4 = (PBool)(((PBool)currentMachine.RandomBoolean()));
                if (TMP_tmp1_4)
                {
                    TMP_tmp2_4 = (PMachineValue)(((PMachineValue)((IPValue)engine)?.Clone()));
                    TMP_tmp3_4 = (Event)(new ePaymentReq((new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)))));
                    TMP_tmp4_4 = (PInt)(((PInt)(0)));
                    TMP_tmp5_4 = (PInt)(((PInt)(1)));
                    TMP_tmp6_3 = (PInt)(((PInt)(500)));
                    TMP_tmp7_3 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount"}, TMP_tmp4_4, TMP_tmp5_4, TMP_tmp6_3)));
                    TMP_tmp3_4.Payload = TMP_tmp7_3;
                    currentMachine.SendEvent(TMP_tmp2_4, (Event)TMP_tmp3_4);
                }
                else
                {
                    TMP_tmp8_2 = (PBool)(((PBool)currentMachine.RandomBoolean()));
                    if (TMP_tmp8_2)
                    {
                        TMP_tmp9_2 = (PMachineValue)(((PMachineValue)((IPValue)engine)?.Clone()));
                        TMP_tmp10_2 = (Event)(new ePaymentReq((new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)))));
                        TMP_tmp11_2 = (PInt)(((PInt)(0)));
                        TMP_tmp12_2 = (PInt)(((PInt)(1)));
                        TMP_tmp13_2 = (PInt)(((PInt)(9000)));
                        TMP_tmp14_2 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount"}, TMP_tmp11_2, TMP_tmp12_2, TMP_tmp13_2)));
                        TMP_tmp10_2.Payload = TMP_tmp14_2;
                        currentMachine.SendEvent(TMP_tmp9_2, (Event)TMP_tmp10_2);
                    }
                    else
                    {
                        TMP_tmp15_2 = (PBool)(((PBool)currentMachine.RandomBoolean()));
                        if (TMP_tmp15_2)
                        {
                            TMP_tmp16_2 = (PMachineValue)(((PMachineValue)((IPValue)engine)?.Clone()));
                            TMP_tmp17_2 = (Event)(new ePaymentReq((new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)))));
                            TMP_tmp18_2 = (PInt)(((PInt)(1)));
                            TMP_tmp19_2 = (PInt)(((PInt)(0)));
                            TMP_tmp20_2 = (PInt)(((PInt)(1000)));
                            TMP_tmp21_2 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount"}, TMP_tmp18_2, TMP_tmp19_2, TMP_tmp20_2)));
                            TMP_tmp17_2.Payload = TMP_tmp21_2;
                            currentMachine.SendEvent(TMP_tmp16_2, (Event)TMP_tmp17_2);
                        }
                        else
                        {
                            TMP_tmp22_2 = (PMachineValue)(((PMachineValue)((IPValue)engine)?.Clone()));
                            TMP_tmp23_2 = (Event)(new ePaymentReq((new PNamedTuple(new string[]{"sender","receiver","amount"},((PInt)0), ((PInt)0), ((PInt)0)))));
                            TMP_tmp24_2 = (PInt)(((PInt)(0)));
                            TMP_tmp25_2 = (PInt)(((PInt)(1)));
                            TMP_tmp26_2 = (PInt)(((PInt)(100000)));
                            TMP_tmp27_2 = (PNamedTuple)((new PNamedTuple(new string[]{"sender","receiver","amount"}, TMP_tmp24_2, TMP_tmp25_2, TMP_tmp26_2)));
                            TMP_tmp23_2.Payload = TMP_tmp27_2;
                            currentMachine.SendEvent(TMP_tmp22_2, (Event)TMP_tmp23_2);
                        }
                    }
                }
                TMP_tmp28_2 = (PInt)((paymentsLeft) - (((PInt)(1))));
                paymentsLeft = TMP_tmp28_2;
            }
        }
        public void Anon_5(Event currentMachine_dequeuedEvent)
        {
            TestDriver currentMachine = this;
            currentMachine.RaiseGotoStateEvent<SendPayments>();
            return;
        }
        [Start]
        [OnEntry(nameof(Anon_3))]
        class Init : State
        {
        }
        [OnEntry(nameof(Anon_4))]
        [OnEventDoAction(typeof(ePaymentResp), nameof(Anon_5))]
        class SendPayments : State
        {
        }
    }
}
namespace PImplementation
{
    public class testXrpPayment {
        public static void InitializeGlobalParams() {
        }
        public static void InitializeLinkMap() {
            PModule.linkMap.Clear();
            PModule.linkMap[nameof(I_TestDriver)] = new Dictionary<string, string>();
            PModule.linkMap[nameof(I_TestDriver)].Add(nameof(I_PaymentEngine), nameof(I_PaymentEngine));
            PModule.linkMap[nameof(I_PaymentEngine)] = new Dictionary<string, string>();
        }
        
        public static void InitializeInterfaceDefMap() {
            PModule.interfaceDefinitionMap.Clear();
            PModule.interfaceDefinitionMap.Add(nameof(I_TestDriver), typeof(TestDriver));
            PModule.interfaceDefinitionMap.Add(nameof(I_PaymentEngine), typeof(PaymentEngine));
        }
        
        public static void InitializeMonitorObserves() {
            PModule.monitorObserves.Clear();
            PModule.monitorObserves[nameof(XrpConservation)] = new List<string>();
            PModule.monitorObserves[nameof(XrpConservation)].Add(nameof(ePaymentResp));
        }
        
        public static void InitializeMonitorMap(ControlledRuntime runtime) {
            PModule.monitorMap.Clear();
            PModule.monitorMap[nameof(I_TestDriver)] = new List<Type>();
            PModule.monitorMap[nameof(I_TestDriver)].Add(typeof(XrpConservation));
            PModule.monitorMap[nameof(I_PaymentEngine)] = new List<Type>();
            PModule.monitorMap[nameof(I_PaymentEngine)].Add(typeof(XrpConservation));
            runtime.RegisterMonitor<XrpConservation>();
        }
        
        
        [PChecker.SystematicTesting.Test]
        public static void Execute(ControlledRuntime runtime) {
            InitializeGlobalParams();
            runtime.RegisterLog(new PCheckerLogTextFormatter());
            runtime.RegisterLog(new PCheckerLogJsonFormatter());
            PModule.runtime = runtime;
            PHelper.InitializeInterfaces();
            PHelper.InitializeEnums();
            InitializeLinkMap();
            InitializeInterfaceDefMap();
            InitializeMonitorMap(runtime);
            InitializeMonitorObserves();
            runtime.CreateStateMachine(typeof(TestDriver), "TestDriver");
        }
    }
}
namespace PImplementation
{
    public class I_PaymentEngine : PMachineValue {
        public I_PaymentEngine (StateMachineId machine, List<string> permissions) : base(machine, permissions) { }
    }
    
    public class I_TestDriver : PMachineValue {
        public I_TestDriver (StateMachineId machine, List<string> permissions) : base(machine, permissions) { }
    }
    
    public partial class PHelper {
        public static void InitializeInterfaces() {
            PInterfaces.Clear();
            PInterfaces.AddInterface(nameof(I_PaymentEngine), nameof(ePaymentReq), nameof(ePaymentResp), nameof(PHalt));
            PInterfaces.AddInterface(nameof(I_TestDriver), nameof(ePaymentReq), nameof(ePaymentResp), nameof(PHalt));
        }
    }
    
}
namespace PImplementation
{
    public partial class PHelper {
        public static void InitializeEnums() {
            PEnum.Clear();
            PEnum.AddEnumElements(new [] {"SUCCESS","INSUFFICIENT_FUNDS"}, new [] {0,1});
        }
    }
    
}
#pragma warning restore 162, 219, 414
