#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting Trove
echo Generating rewritten trove library
java $JAVA_OPTS ui.RewriteAllSources --bodies -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $TROVE/src

echo Going to directory containing rewritten trove
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/trove-2.1.0/src


echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -d build `pathlist_recursive.py '*.java' gnu`

echo Going back to original directory
cd ../../..
