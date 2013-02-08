
JSCIENCE=../../libs_nov2010_work/jscience
SRC=$JSCIENCE/src/main/java
LIBS=$SCALA_HOME/lib/scala-library.jar
# LIBS=$LIBS:$JSCIENCE/lib/javolution.jar
# LIBS=$LIBS:$JSCIENCE/lib/geoapi.jar

set -ex
# Rewriting Trove
echo Generating rewritten trove library
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

# echo Going to directory containing rewritten trove
# cd rewrittenSources/libs_nov2010_work/trove-2.1.0/
# 
# echo Compiling generated sources
# javac -d .  -sourcepath src:output/gen_src output/gen_src/gnu/trove/*.java output/gen_src/gnu/trove/decorator/*.java
# javac -d . src/gnu/trove/*.java src/gnu/trove/benchmark/*.java src/gnu/trove/generate/Generate.java
# 
# echo Going back to original directory
# cd ../../..
