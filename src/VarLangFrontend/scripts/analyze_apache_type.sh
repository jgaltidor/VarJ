SRC=$APACHE/src/java
LIBS=$SCALA_HOME/lib/scala-library.jar

set -ex
# Analyze Apache Type
echo Analyzing Apache Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP --bodies -v 3 -t $1 $SRC
