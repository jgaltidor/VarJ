#! /bin/bash
source ./scripts/setenv.sh
set -ex
java $JAVA_OPTS ui.InferStats --bodies -j -classpath -j $LIBCP --texout testtex/table.tex $JAVASTAR:Java
