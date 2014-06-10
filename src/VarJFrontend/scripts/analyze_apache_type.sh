#! /bin/bash

set -ex
# Analyze Apache Type
echo Analyzing Apache Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP --bodies -v 3 -t $1 $APACHE/src/java
