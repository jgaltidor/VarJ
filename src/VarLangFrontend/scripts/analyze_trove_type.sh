TROVE=../../libs_nov2010_fix/trove-2.1.0
SRC=$JSCIENCE/src
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Analyze Trove Type
echo Analyzing Trove Type $1
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $TROVE/src  $TROVE/output/gen_src
