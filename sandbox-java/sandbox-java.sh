#!/usr/bin/env bash
set -euo pipefail

if [ $# -lt 1 ]; then
  echo "Usage: ./sandbox-java.sh [build|shell|compile|run|clean]"
  exit 1
fi

case "$1" in
  build)
    DOCKER_UID="$(id -u)" DOCKER_GID="$(id -g)" docker compose build
    ;;
  shell)
    DOCKER_UID="$(id -u)" DOCKER_GID="$(id -g)" docker compose run --rm java
    ;;
  compile)
    DOCKER_UID="$(id -u)" DOCKER_GID="$(id -g)" docker compose run --rm java sh -lc "rm -rf /tmp/out && mkdir -p /tmp/out && javac -d /tmp/out \$(find src -name '*.java')"
    ;;
  run)
    DOCKER_UID="$(id -u)" DOCKER_GID="$(id -g)" docker compose run --rm java sh -lc "rm -rf /tmp/out && mkdir -p /tmp/out && javac -d /tmp/out \$(find src -name '*.java') && java -cp /tmp/out Main"
    ;;
  clean)
    DOCKER_UID="$(id -u)" DOCKER_GID="$(id -g)" docker compose run --rm java sh -lc "rm -rf /tmp/out"
    ;;
  *)
    echo "Unknown command: $1"
    echo "Usage: ./sandbox-java.sh [build|shell|compile|run|clean]"
    exit 1
    ;;
esac
