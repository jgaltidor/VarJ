SCALA_HOME=../3rd_party_libs/scala-2.9.3
# Classpath passed to Java
JAVACP=$SCALA_HOME/lib/scala-library.jar:VarJ.jar:$CLASSPATH
JAVA_OPTS="-ea -cp $JAVACP"

java $JAVA_OPTS ui.RewriteAllSources $*
