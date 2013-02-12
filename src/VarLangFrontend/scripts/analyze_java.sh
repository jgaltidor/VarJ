JAVA_OPTS="-Xmx2g"
ROOTOFLIBS=../../libs_nov2010_fix
APACHE=$ROOTOFLIBS/collections-generic-4.01/src/java/org/apache/commons
GUAVA=$ROOTOFLIBS/guava-libraries-read-only/src
JAVASTAR=$ROOTOFLIBS/jdk1.6.0_06_src
JPAUL=$ROOTOFLIBS/jpaul/src
JSCIENCE=$ROOTOFLIBS/jscience-4.3/src
TROVE=$ROOTOFLIBS/trove-2.1.0/src

# Add Scala library to class
CLASSPATH=$SCALA_HOME/lib/scala-library.jar:$CLASSPATH

echo java -Xmx1g -classpath $CLASSPATH ui.InferStats --texout testtex/table.tex $JAVASTAR:Java
java -Xmx1g -classpath $CLASSPATH ui.InferStats --texout testtex/table.tex $JAVASTAR:Java
