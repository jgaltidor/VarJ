#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting java.*
echo Generating rewritten apache library
java $JAVA_OPTS ui.RewriteAllSources --bodies -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $JAVASTAR

echo Going to directory containing rewritten apache
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/jdk1.6.0_06_src

echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' java/util`

echo Going back to original directory
cd ../../..
