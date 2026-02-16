#!/usr/bin/env bash
#
# End-to-end PObserve pipeline for XRP payment verification.
#
# Usage:
#   ./formal/run-pobserve.sh
#
# Steps:
#   1. Runs the C++ PObservePayment test -> writes /tmp/pobserve_payment.log
#   2. Runs the Java PObserve test -> checks the log against XrpConservation spec
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
LOG_FILE="/tmp/pobserve_payment.log"

RIPPLED="$REPO_ROOT/.build/xrpld"

rm -f "$LOG_FILE"

echo "=== Step 1: Run C++ PObservePayment test ==="
POBSERVE_LOG="$LOG_FILE" "$RIPPLED" --unittest=PObservePayment 2>&1 | tail -50
echo ""

echo "=== Log file contents ==="
cat "$LOG_FILE"
echo ""

echo "=== Step 2: Run PObserve Java test ==="
cd "$SCRIPT_DIR/xrp-payment-observe"
mvn test -q -Dtest=XrpPaymentPObserveTest#testRippledOutput -Dpobserve.logfile="$LOG_FILE" 2>&1 | tail -10
echo ""

echo "=== Done: XrpConservation spec verified against rippled output ==="
