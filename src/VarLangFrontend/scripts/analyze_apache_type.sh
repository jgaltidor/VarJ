
APACHE=../../libs_nov2010_work/collections-generic-4.01
SRC=$APACHE/src/java
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Analyze Apache Type
echo Analyzing Apache Type $1
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $SRC
