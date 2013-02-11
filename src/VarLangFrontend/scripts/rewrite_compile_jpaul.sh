
JPAUL=../../libs_nov2010_work/jpaul
SRC=$JPAUL/src
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Rewriting JPaul
echo Generating rewritten jpaul library
java -Xmx2g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC
exit

echo Going to directory containing rewritten jpaul
cd rewrittenSources/libs_nov2010_work/jpaul/src

echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' jpaul`

echo Going back to original directory
cd ../../../../..

