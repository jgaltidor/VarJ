#! /bin/bash
source ./scripts/setenv.sh

set -ex
# rlwrap jdb $JAVA_OPTS ui.InferStats -j -classpath -j $LIBCP --texout testtex/table.tex \
java $JAVA_OPTS ui.InferStats -j -classpath -j $LIBCP --texout testtex/table.tex \
	--json sigstats.json \
  $JAVASTAR:Java \
  $JSCIENCE/src:JScience \
  $APACHE/src/java:Apache \
  $GUAVA/src:Guava \
  $TROVE/src:Trove \
  $JPAUL/src:JPaul 
