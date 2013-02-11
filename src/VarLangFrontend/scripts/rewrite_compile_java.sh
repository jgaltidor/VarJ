
JAVASTAR=../../libs_nov2010_work/jdk1.6.0_06_src
SRC=$JAVASTAR
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Rewriting Apache Collections Library
echo Generating rewritten apache library
java -Xmx2g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

echo Going to directory containing rewritten apache
cd rewrittenSources/libs_nov2010_work/jdk1.6.0_06_src

echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' java/util`

echo Going back to original directory
cd ../../..

