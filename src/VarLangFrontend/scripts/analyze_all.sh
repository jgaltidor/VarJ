source ./scripts/setenv.sh

set -ex
java $JAVA_OPTS ui.InferStats -j -classpath -j $LIBCP --texout testtex/table.tex \
  $JAVASTAR:Java \
  $JSCIENCE/src:JScience \
  $APACHE/src/java:Apache \
  $GUAVA/src:Guava \
  $TROVE/src:Trove \
  $JPAUL/src:JPaul 
