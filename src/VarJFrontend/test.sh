set -ex

SCALA_HOME=../3rd_party_libs/scala-2.9.3
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

# rewritten test pair 1
java $JAVA_OPTS ui.RewriteClasses -m rewriteinfo1_sig.txt -d rewrittenSources1_sig -t test.Seller -t test.RList unittests
java $JAVA_OPTS ui.RewriteClasses -v 3 --bodies -m rewriteinfo1_bodies.txt -d rewrittenSources1_bodies -t test.Seller -t test.RList unittests

echo Compiling generated sources in rewrittenSources1_sig
cd rewrittenSources1_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources1_bodies
cd rewrittenSources1_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..

# rewritten test pair 2
java $JAVA_OPTS ui.RewriteAllSources -v 3 -m rewriteinfo2_sig.txt -d rewrittenSources2_sig unittests
java $JAVA_OPTS ui.RewriteAllSources -v 3 --bodies -m rewriteinfo2_bodies.txt -d rewrittenSources2_bodies unittests

echo Compiling generated sources in rewrittenSources2_sig
cd rewrittenSources2_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources2_bodies
cd rewrittenSources2_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..

# rewritten test pair 3
java $JAVA_OPTS ui.RewriteSelected --declsfile unittests/includesExcludes.json \
                                     -v 3 \
                                     -m rewriteinfo3_sig.txt \
                                     -d rewrittenSources3_sig \
                                     unittests

java $JAVA_OPTS ui.RewriteSelected --bodies \
                                     --declsfile unittests/includesExcludes.json \
                                     -v 3 \
                                     -m rewriteinfo3_bodies.txt \
                                     -d rewrittenSources3_bodies \
                                     unittests

echo Compiling generated sources in rewrittenSources3_sig
cd rewrittenSources3_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources3_bodies
cd rewrittenSources3_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..

# rewritten test pair 4

java $JAVA_OPTS ui.RewriteSelected --declsfile unittests/paperexample.json \
                                     -v 3 \
                                     -m rewriteinfo4_sig.txt \
                                     -d rewrittenSources4_sig \
                                     unittests/PaperExample.java

java $JAVA_OPTS ui.RewriteSelected --bodies \
                                     --declsfile unittests/paperexample.json \
                                     -v 3 \
                                     -m rewriteinfo4_bodies.txt \
                                     -d rewrittenSources4_bodies \
                                     unittests/PaperExample.java

echo Compiling generated sources in rewrittenSources4_sig
cd rewrittenSources4_sig/unittests
javac *.java
echo Going back to original directory
cd ../..

echo Compiling generated sources in rewrittenSources4_bodies
cd rewrittenSources4_bodies/unittests
javac *.java
echo Going back to original directory
cd ../..
