# JastAddJ doesn't handle UTF-8 well.
source ./scripts/setenv.sh
set -ex
# Rewriting JScience
echo Generating rewritten jscience library
java $JAVA_OPTS ui.RewriteAllSources -j -classpath -j $LIBCP -m rewriteinfo.txt -d rewrittenSources $JSCIENCE/src

echo Going to directory containing rewritten apache
cd rewrittenSources/libs_nov2010_fix/jscience-4.3/src

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBCP:$CLASSPATH -encoding utf8 -d build `pathlist_recursive.py '*.java' org:javax`

echo Going back to original directory
cd ../../../..
