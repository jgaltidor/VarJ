
# JastAddJ can't parse with UTF-8 encoding in source.

JSCIENCE=../../libs_nov2010_work/jscience-4.3
SRC=$JSCIENCE/src
JSCIENCE_FULLPATH=/Users/jaltidor/allwork/nonrepo/work/school/courses/front_end_lang/projects/infer_variance_jga/VarLang/svnstuff/trunk/libs_nov2010_work/jscience-4.3

LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:$JSCIENCE_FULLPATH/lib/javolution.jar
LIBS=$LIBS:$JSCIENCE_FULLPATH/lib/geoapi.jar

set -ex
# Analyze JScience Type
echo Analyzing JScience Type $1
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $SRC
