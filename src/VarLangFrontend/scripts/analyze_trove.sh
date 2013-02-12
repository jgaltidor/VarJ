source ./scripts/setenv.sh

set -ex
java $JAVA_OPTS ui.InferStats --texout testtex/table.tex $TROVE/src:Trove
