#! /bin/bash
source ./scripts/setenv.sh
set -ex

java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $JAVASTAR
java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $JSCIENCE/src
java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $APACHE/src
java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $GUAVA/src
java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $TROVE/src
java $JAVA_OPTS ui.PrintFBoundInfo -j -classpath -j $LIBCP $JPAUL/src
