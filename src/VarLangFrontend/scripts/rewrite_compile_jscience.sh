
# JastAddJ can't parse with UTF-8 encoding in source.
exit

JSCIENCE=../../libs_nov2010_work/jscience-4.3
SRC=$JSCIENCE/src
LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:$JSCIENCE/lib/javolution.jar
LIBS=$LIBS:$JSCIENCE/lib/geoapi.jar

set -ex
# Rewriting Trove
echo Generating rewritten trove library
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

