ROOTOFLIBS=../../libs_nov2010_fix
APACHE=$ROOTOFLIBS/collections-generic-4.01
GUAVA=$ROOTOFLIBS/guava-libraries-read-only
JAVASTAR=$ROOTOFLIBS/jdk1.6.0_06_src
JPAUL=$ROOTOFLIBS/jpaul-2.5.1
JSCIENCE=$ROOTOFLIBS/jscience-4.3
TROVE=$ROOTOFLIBS/trove-2.1.0

# Classpath passed to Java
JAVACP=$SCALA_HOME/lib/scala-library.jar:$CLASSPATH
# Adding libraries used by Trove

# Get full (canonical) paths of lib directories
APACHE_FP=`./scripts/realpath.py $APACHE`
GUAVA_FP=`./scripts/realpath.py $GUAVA`
JAVASTAR_FP=`./scripts/realpath.py $JAVASTAR`
JPAUL_FP=`./scripts/realpath.py $JPAUL`
JSCIENCE_FP=`./scripts/realpath.py $JSCIENCE`
TROVE_FP=`./scripts/realpath.py $TROVE`

# Classpath passed to Jastadd
# junit also used by JPaul
LIBCP=$LIBCP:$TROVE_FP/lib/junit.jar
# Adding libraries used by JScience
LIBCP=$LIBCP:$JSCIENCE_FP/lib/javolution.jar
LIBCP=$LIBCP:$JSCIENCE_FP/lib/geoapi.jar
# Adding libraries used by Guava
JAVA5_CLASSES=/System/Library/Frameworks/JavaVM.framework/Versions/1.5/Classes/classes.jar
LIBCP=$LIBCP:$JAVA5_CLASSES
LIBCP=$LIBCP:$GUAVA_FP/lib/jsr305.jar

JAVA_OPTS="-Xmx2g -classpath $JAVACP -ea"
