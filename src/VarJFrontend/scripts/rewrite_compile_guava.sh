#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting Guava
echo Generating rewritten Guava library
# rlwrap jdb $JAVA_OPTS ui.RewriteAllSources -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $GUAVA/src
java $JAVA_OPTS ui.RewriteAllSources -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $GUAVA/src

echo Going to directory containing rewritten apache
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/guava-libraries-read-only/src

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -d build `pathlist_recursive.py '*.java' com`

echo Going back to original directory
cd ../../../../..
