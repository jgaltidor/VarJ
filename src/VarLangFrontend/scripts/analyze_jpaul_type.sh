JPAUL=../../libs_nov2010_fix/jpaul
SRC=$JPAUL/src
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Analyze JPaul Type
echo Analyzing JPaul Type $1
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $SRC
