set -ex

SCALA_HOME=/opt/myinstalls/programs/scala
# Classpath passed to Java
JAVACP=$SCALA_HOME/lib/scala-library.jar:VarJ.jar:$CLASSPATH
JAVA_OPTS="-ea -cp $JAVACP"

java $JAVA_OPTS ui.FilesCLParser unittests/Lists.java
java $JAVA_OPTS ui.FilesCLParser unittests
java $JAVA_OPTS ui.FilesCLParser --bodies unittests

java $JAVA_OPTS ui.LookupVar --generic test.WList unittests/Lists.java
java $JAVA_OPTS ui.LookupVar --generic test.WList -v 3 unittests/Lists.java

java $JAVA_OPTS ui.AnalyzeType --type test.Animal unittests
java $JAVA_OPTS ui.AnalyzeType --type test.IList unittests

java $JAVA_OPTS ui.InferStats --texout testtex/table.tex unittests/Lists.java:lists unittests/PLDITest.java:pldi
java $JAVA_OPTS ui.InferStats --texout testtex/table.tex --json sig.json unittests:unitests
java $JAVA_OPTS ui.InferStats --texout testtex/table.tex --bodies --json bod.json unittests:unitests

java $JAVA_OPTS ui.TexTable testtex/table.tex sig.json bod.json

java $JAVA_OPTS ui.RewriteClasses -m rewriteinfo_sig.txt -d rewrittenSources_sig -t test.Seller -t test.RList unittests
java $JAVA_OPTS ui.RewriteClasses -v 3 --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies -t test.Seller -t test.RList unittests

echo Compiling generated sources in rewrittenSources_sig
cd rewrittenSources_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources_bodies
cd rewrittenSources_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..

java $JAVA_OPTS ui.RewriteAllSources -v 3 -m rewriteinfo_sig.txt -d rewrittenSources_sig unittests
java $JAVA_OPTS ui.RewriteAllSources -v 3 --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies unittests

echo Compiling generated sources in rewrittenSources_sig
cd rewrittenSources_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources_bodies
cd rewrittenSources_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..

java $JAVA_OPTS ui.RewriteSelected --declsfile unittests/includesExcludes.json \
                                     -v 3 \
                                     -m rewriteinfo_sig.txt \
                                     -d rewrittenSources_sig \
                                     unittests

java $JAVA_OPTS ui.RewriteSelected --bodies \
                                     --declsfile unittests/includesExcludes.json \
                                     -v 3 \
                                     -m rewriteinfo_bodies.txt \
                                     -d rewrittenSources_bodies \
                                     unittests

java $JAVA_OPTS ui.RewriteSelected --bodies \
                                     --declsfile unittests/paperexample.json \
                                     -v 3 \
                                     -m rewriteinfo_bodies.txt \
                                     -d rewrittenSources_bodies \
                                     unittests/PaperExample.java

echo Compiling generated sources in rewrittenSources_sig
cd rewrittenSources_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources_bodies
cd rewrittenSources_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..
