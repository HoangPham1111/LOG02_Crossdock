#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")/.."
export RUN_DB_TESTS=true
export DB_URL="${DB_URL:-jdbc:postgresql://localhost:5432/crossdock_test}"
mvn test
