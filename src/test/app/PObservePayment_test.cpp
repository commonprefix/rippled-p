#include <test/jtx.h>

#include <xrpl/protocol/Feature.h>

#include <cstdlib>
#include <iostream>

namespace xrpl {
namespace test {

struct PObservePayment_test : public beast::unit_test::suite
{
    void
    run() override
    {
        using namespace jtx;

        testcase("XRP Payments with PObserve logging");

        // POBSERVE instrumentation is in Payment.cpp.
        // When POBSERVE_LOG env var is set, Payment::doApply() emits
        // structured log lines for every XRP direct payment.

        auto const alice = Account("alice");
        auto const bob = Account("bob");

        Env env(*this, testable_amendments());
        env.fund(XRP(20000), alice, bob);
        env.close();

        // Payment 1: alice -> bob, 100 XRP (success)
        env(pay(alice, bob, XRP(100)));
        BEAST_EXPECT(env.ter() == tesSUCCESS);

        // Payment 2: bob -> alice, 50 XRP (success)
        env(pay(bob, alice, XRP(50)));
        BEAST_EXPECT(env.ter() == tesSUCCESS);

        // Payment 3: alice -> bob, way over balance (fail)
        env(pay(alice, bob, XRP(999999)), ter(tecUNFUNDED_PAYMENT));

        if (auto const* logPath = std::getenv("POBSERVE_LOG"))
            log << "PObserve log written to: " << logPath << std::endl;
    }
};

BEAST_DEFINE_TESTSUITE_PRIO(PObservePayment, app, xrpl, 1);

}  // namespace test
}  // namespace xrpl
