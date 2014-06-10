#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting Apache Collections Library
echo Generating rewritten apache library
java $JAVA_OPTS ui.RewriteAllSources -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $APACHE/src/java

echo Going to directory containing rewritten apache
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/collections-generic-4.01/src/java

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -d build `pathlist_recursive.py '*.java' org`

echo Going back to original directory
cd ../../../../..
