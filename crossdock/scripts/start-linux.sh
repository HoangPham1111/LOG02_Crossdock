#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")/.."
docker compose up --build -d
docker compose logs -f app
