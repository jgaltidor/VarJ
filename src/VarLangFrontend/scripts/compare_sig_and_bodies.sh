set -ex

./scripts/analyze_all.sh
./scripts/analyze_all_bodies.sh
scala ui.Table2 testtex/table.tex sigstats.json bodstats.json
