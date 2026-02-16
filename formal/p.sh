#!/usr/bin/env bash
#
# Compile P model, run verification, and generate PObserve Java files.
#
# Usage:
#   ./formal/p.sh
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
P_DIR="$SCRIPT_DIR/xrp-payment"
OBSERVE_DIR="$SCRIPT_DIR/xrp-payment-observe"

echo "=== Step 1: Compile P model (PEx) ==="
cd "$P_DIR"
p compile --mode pex -o ./PEx

echo ""
echo "=== Step 2: Verify with PEx ==="
p check --mode pex

echo ""
echo "=== Step 3: Compile P model (PObserve) ==="
p compile --mode pobserve -o ./PObserve

echo ""
echo "=== Step 4: Copy generated Java files ==="
cp PObserve/PObserve/PMachines.java "$OBSERVE_DIR/src/main/java/XrpPayment/pobserve/"
cp PObserve/PObserve/PEvents.java "$OBSERVE_DIR/src/main/java/XrpPayment/pobserve/"
cp PObserve/PObserve/PTypes.java "$OBSERVE_DIR/src/main/java/XrpPayment/pobserve/"
echo "Copied PEvents.java, PMachines.java, PTypes.java"

echo ""
echo "=== Step 5: Build and test Java project ==="
cd "$OBSERVE_DIR"
mvn clean test -q -Dtest='XrpPaymentPObserveTest#test* AND NOT XrpPaymentPObserveTest#testRippledOutput' 2>&1 | tail -10

echo ""
echo "=== Done ==="
