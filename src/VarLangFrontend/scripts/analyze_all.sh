JAVA_OPTS="-Xmx1g"
ROOTOFLIBS=../../libs_nov2010
APACHE=$ROOTOFLIBS/collections-generic-4.01/src/java/org/apache/commons
GUAVA=$ROOTOFLIBS/guava-libraries-read-only/src
JAVASTAR=$ROOTOFLIBS/jdk1.6.0_06_src
JPAUL=$ROOTOFLIBS/jpaul/src
JSCIENCE=$ROOTOFLIBS/jscience/src/main/java
TROVE=$ROOTOFLIBS/trove-2.1.0/src

# Add Scala library to class
CLASSPATH=$SCALA_HOME/lib/scala-library.jar:$CLASSPATH

echo java -Xmx1g -classpath $CLASSPATH ui.InferStats --texout testtex/table.tex \
  $JAVASTAR:Java \
  $JSCIENCE:JScience \
  $APACHE:Apache \
  $GUAVA:Guava \
  $TROVE:Trove \
  $JPAUL:JPaul 


java -Xmx1g -classpath $CLASSPATH ui.InferStats --texout testtex/table.tex \
  $JAVASTAR:Java \
  $JSCIENCE:JScience \
  $APACHE:Apache \
  $GUAVA:Guava \
  $TROVE:Trove \
  $JPAUL:JPaul 
