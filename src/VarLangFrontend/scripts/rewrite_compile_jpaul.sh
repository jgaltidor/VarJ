JPAUL=../../libs_nov2010_fix/jpaul-2.5.1
SRC=$JPAUL/src
LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:$JPAUL/lib/junit.jar

set -ex
# Rewriting JPaul
echo Generating rewritten jpaul library
java -Xmx2g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

echo Going to directory containing rewritten jpaul
cd rewrittenSources/libs_nov2010_fix/jpaul-2.5.1/src

echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' jpaul`

echo Going back to original directory
cd ../../../..
