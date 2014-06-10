#! /bin/bash

# Configs
ANALYZEDLIBS_BASENAME=analyzed_libs
ANALYZEDLIBS=../../$ANALYZEDLIBS_BASENAME
SCALA_HOME=../3rd_party_libs/scala-2.9.3
# classes.jar used for analyzing Guava library (Release 8)
JAVA5_CLASSES=../3rd_party_libs/java-1.5/Classes/classes.jar

APACHE=$ANALYZEDLIBS/collections-generic-4.01
GUAVA=$ANALYZEDLIBS/guava-libraries-read-only
JAVASTAR=$ANALYZEDLIBS/jdk1.6.0_06_src
JPAUL=$ANALYZEDLIBS/jpaul-2.5.1
JSCIENCE=$ANALYZEDLIBS/jscience-4.3
TROVE=$ANALYZEDLIBS/trove-2.1.0

# Classpath passed to Java
JAVACP=$SCALA_HOME/lib/scala-library.jar:VarJ.jar:$CLASSPATH

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
LIBCP=$LIBCP:$JAVA5_CLASSES
LIBCP=$LIBCP:$GUAVA_FP/lib/jsr305.jar

# JAVA_OPTS="-Xmx2g -classpath $JAVACP"
JAVA_OPTS="-Xmx2g -ea -classpath $JAVACP"


# Add scripts directory to the PATH
PATH=`./scripts/realpath.py ./scripts`:$PATH
