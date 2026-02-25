#!/usr/bin/env bash
# Sample script run by the pipeline - proves this stage ran from your branch
set -e
echo "=== Sample script started ==="
echo "Running from: $(pwd)"
echo "Branch (from env): ${GIT_BRANCH:-unknown}"
echo "=== Sample script finished ==="
