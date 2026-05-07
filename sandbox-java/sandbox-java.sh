#!/usr/bin/env bash
set -euo pipefail

if [ $# -lt 1 ]; then
  echo "Usage: ./sandbox-java.sh [build|shell|compile|run|clean]"
  exit 1
fi

case "$1" in
  build)
    docker compose build
    ;;
  shell)
    docker compose run --rm java
    ;;
  compile)
    docker compose run --rm java sh -lc "mkdir -p out && javac -d out \\$(find src -name '*.java')"
    ;;
  run)
    docker compose run --rm java sh -lc "mkdir -p out && javac -d out \\$(find src -name '*.java') && java -cp out Main"
    ;;
  clean)
    rm -rf out/*
    ;;
  *)
    echo "Unknown command: $1"
    echo "Usage: ./sandbox-java.sh [build|shell|compile|run|clean]"
    exit 1
    ;;
esac
