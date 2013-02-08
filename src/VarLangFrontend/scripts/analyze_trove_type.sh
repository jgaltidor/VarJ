
TROVE=../../libs_nov2010_work/trove-2.1.0
SRC=$JSCIENCE/src
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Rewriting Trove
echo Generating rewritten trove library
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $TROVE/src  $TROVE/output/gen_src
