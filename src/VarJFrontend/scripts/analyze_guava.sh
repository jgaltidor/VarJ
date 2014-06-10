#! /bin/bash
source ./scripts/setenv.sh
set -ex
java $JAVA_OPTS ui.InferStats -j -classpath -j $LIBCP --texout testtex/table.tex $GUAVA/src:Guava
