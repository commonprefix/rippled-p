#!/usr/bin/env bash
#
# End-to-end PObserve pipeline for XRP payment verification.
#
# Usage:
#   ./formal/run-pobserve.sh [path-to-rippled-binary]
#
# Steps:
#   1. Runs the C++ PObservePayment test → writes /tmp/pobserve_payment.log
#   2. Runs the Java PObserve test → checks the log against XrpConservation spec
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
LOG_FILE="/tmp/pobserve_payment.log"

# Find rippled binary
RIPPLED="${1:-}"
if [ -z "$RIPPLED" ]; then
    # Try common build locations
    for candidate in \
        "$REPO_ROOT/build/rippled" \
        "$REPO_ROOT/build/Debug/rippled" \
        "$REPO_ROOT/build/Release/rippled" \
        "$REPO_ROOT/.build/rippled"; do
        if [ -x "$candidate" ]; then
            RIPPLED="$candidate"
            break
        fi
    done
fi

if [ -z "$RIPPLED" ] || [ ! -x "$RIPPLED" ]; then
    echo "ERROR: rippled binary not found."
    echo "Usage: $0 [path-to-rippled-binary]"
    echo ""
    echo "Build rippled first, then pass the binary path."
    exit 1
fi

echo "=== Step 1: Running C++ PObservePayment test ==="
echo "    Binary: $RIPPLED"
echo "    Log:    $LOG_FILE"
POBSERVE_LOG="$LOG_FILE" "$RIPPLED" --unittest=PObservePayment 2>&1 | tail -5
echo ""

if [ ! -f "$LOG_FILE" ]; then
    echo "ERROR: $LOG_FILE was not created."
    exit 1
fi

echo "=== Log file contents ==="
cat "$LOG_FILE"
echo ""

echo "=== Step 2: Running PObserve Java test ==="
cd "$SCRIPT_DIR/xrp-payment-observe"
mvn test -q -Dtest=XrpPaymentPObserveTest#testRippledOutput \
    -Dpobserve.logfile="$LOG_FILE" 2>&1 | tail -10
echo ""

echo "=== Done: XrpConservation spec verified against rippled output ==="
