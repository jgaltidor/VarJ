APACHE=../../libs_nov2010_fix/collections-generic-4.01
SRC=$APACHE/src/java
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Rewriting Apache Collections Library
echo Generating rewritten apache library
java -Xmx2g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

echo Going to directory containing rewritten apache
cd rewrittenSources/libs_nov2010_fix/collections-generic-4.01/src/java

echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' org`

echo Going back to original directory
cd ../../../../..
