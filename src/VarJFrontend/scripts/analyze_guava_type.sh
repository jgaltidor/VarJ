#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Analyze Guava Type
echo Analyzing Guava Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP --bodies -v 3 -t $1 $GUAVA/src
