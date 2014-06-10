#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting JScience
echo Generating rewritten jscience library
# rlwrap jdb $JAVA_OPTS ui.RewriteAllSources --bodies -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $JSCIENCE/src
java $JAVA_OPTS ui.RewriteAllSources --bodies -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $JSCIENCE/src

echo Going to directory containing rewritten apache
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/jscience-4.3/src

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -encoding utf8 -d build `pathlist_recursive.py '*.java' org:javax`

echo Going back to original directory
cd ../../../..
