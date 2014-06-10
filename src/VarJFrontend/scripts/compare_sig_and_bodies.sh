#! /bin/bash
source ./scripts/setenv.sh
set -ex

./scripts/analyze_all.sh
./scripts/analyze_all_bodies.sh
java $JAVA_OPTS ui.TexTable testtex/table.tex sigstats.json bodstats.json
