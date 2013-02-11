GUAVA=../../libs_nov2010_work/guava-libraries-read-only
SRC=$GUAVA/src
GUAVA_FULLPATH=~/allwork/nonrepo/work/school/courses/front_end_lang/projects/infer_variance_jga/VarLang/svnstuff/trunk/libs_nov2010_work/guava-libraries-read-only
LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:/System/Library/Frameworks/JavaVM.framework/Versions/1.5/Classes/classes.jar
LIBS=$LIBS:$GUAVA_FULLPATH/lib/jsr305.jar

set -ex
# Analyze Guava Type
echo Analyzing Guava Type $1
java -Xmx1g -classpath $LIBS:$CLASSPATH  ui.AnalyzeType -t $1  $SRC
