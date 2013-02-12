GUAVA=../../libs_nov2010_fix/guava-libraries-read-only
SRC=$GUAVA/src
GUAVA_FULLPATH=~/allwork/nonrepo/work/school/courses/front_end_lang/projects/infer_variance_jga/VarLang/svnstuff/trunk/libs_nov2010_fix/guava-libraries-read-only
LIBS=$SCALA_HOME/lib/scala-library.jar
LIBS=$LIBS:/System/Library/Frameworks/JavaVM.framework/Versions/1.5/Classes/classes.jar
LIBS=$LIBS:$GUAVA_FULLPATH/lib/jsr305.jar

set -ex
# Rewriting Guava
echo Generating rewritten Guava library
# rlwrap jdb -Xmx2g -classpath $LIBS:$CLASSPATH ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC
java -Xmx2g -classpath $LIBS:$CLASSPATH ui.RewriteAllSources -m rewriteinfo.txt -d rewrittenSources $SRC

echo Going to directory containing rewritten apache
cd rewrittenSources/libs_nov2010_fix/guava-libraries-read-only/src

echo Compiling generated sources
mkdir -p build
javac -classpath $LIBS:$CLASSPATH -d build `pathlist_recursive.py '*.java' com`

echo Going back to original directory
cd ../../../../..
