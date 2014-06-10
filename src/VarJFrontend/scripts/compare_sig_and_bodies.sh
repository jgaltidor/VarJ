#! /bin/bash
set -ex

./scripts/analyze_all.sh
./scripts/analyze_all_bodies.sh
scala ui.TexTable testtex/table.tex sigstats.json bodstats.json
