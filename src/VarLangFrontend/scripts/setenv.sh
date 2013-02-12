ROOTOFLIBS=../../libs_nov2010_fix
APACHE=$ROOTOFLIBS/collections-generic-4.01
GUAVA=$ROOTOFLIBS/guava-libraries-read-only
JAVASTAR=$ROOTOFLIBS/jdk1.6.0_06_src
JPAUL=$ROOTOFLIBS/jpaul
JSCIENCE=$ROOTOFLIBS/jscience-4.3
TROVE=$ROOTOFLIBS/trove-2.1.0

# Add Scala library to class
CLASSPATH=$SCALA_HOME/lib/scala-library.jar:$CLASSPATH
# Adding libraries used by Trove
CLASSPATH=$CLASSPATH:$TROVE/lib/junit.jar
# Adding libraries used by JScience
CLASSPATH=$CLASSPATH:$JSCIENCE/lib/javolution.jar
CLASSPATH=$CLASSPATH:$JSCIENCE/lib/geoapi.jar
# Adding libraries used by Guava
CLASSPATH=$CLASSPATH:/System/Library/Frameworks/JavaVM.framework/Versions/1.5/Classes/classes.jar
CLASSPATH=$CLASSPATH:$GUAVA/lib/jsr305.jar

JAVA_OPTS="-Xmx2g -classpath $CLASSPATH"
