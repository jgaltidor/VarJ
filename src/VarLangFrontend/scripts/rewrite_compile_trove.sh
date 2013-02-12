TROVE=../../libs_nov2010_fix/trove-2.1.0
SRC=$TROVE/src
LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:$TROVE/lib/junit.jar

set -ex
# Rewriting Trove
echo Generating rewritten trove library
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources  $SRC

echo Going to directory containing rewritten trove
cd rewrittenSources/libs_nov2010_fix/trove-2.1.0/src


echo Compiling generated sources
mkdir -p build
javac -d build `pathlist_recursive.py '*.java' gnu`

echo Going back to original directory
cd ../../..
