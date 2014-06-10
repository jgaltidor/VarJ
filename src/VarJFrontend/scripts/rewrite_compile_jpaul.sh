#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Rewriting JPaul
echo Generating rewritten jpaul library
java $JAVA_OPTS ui.RewriteAllSources -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $JPAUL/src

echo Going to directory containing rewritten jpaul
cd rewrittenSources/$ANALYZEDLIBS_BASENAME/jpaul-2.5.1/src

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -d build `pathlist_recursive.py '*.java' jpaul`

echo Going back to original directory
cd ../../../..
