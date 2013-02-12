# JastAddJ can't parse with UTF-8 encoding in source.

JSCIENCE=../../libs_nov2010_fix/jscience-4.3
SRC=$JSCIENCE/src
JSCIENCE_FULLPATH=/Users/jaltidor/allwork/nonrepo/work/school/courses/front_end_lang/projects/infer_variance_jga/VarLang/svnstuff/trunk/libs_nov2010_fix/jscience-4.3

LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:$JSCIENCE_FULLPATH/lib/javolution.jar
LIBS=$LIBS:$JSCIENCE_FULLPATH/lib/geoapi.jar

set -ex
# Rewriting JScience
echo Generating rewritten jscience library
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC


echo Going to directory containing rewritten apache
cd rewrittenSources/libs_nov2010_fix/jscience-4.3/src

echo Compiling generated sources
mkdir -p build
javac -encoding utf8 -classpath $LIBS:$CLASSPATH -d build `pathlist_recursive.py '*.java' org:javax`

exit 
echo Going back to original directory
cd ../../../..
