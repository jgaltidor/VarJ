set -ex

SCALA_OPTS="-J-ea"

java -ea ui.BaseCLParser unittests/Lists.java
java -ea ui.BaseCLParser unittests
java -ea ui.BaseCLParser --bodies unittests

java -ea ui.LookupVar --generic test.WList unittests/Lists.java
java -ea ui.LookupVar --generic test.WList -v 3 unittests/Lists.java

java -ea ui.AnalyzeType --type test.Animal unittests
java -ea ui.AnalyzeType --type test.IList unittests


scala $SCALA_OPTS ui.InferStats --texout testtex/table.tex unittests/Lists.java:lists unittests/PLDITest.java:pldi
scala $SCALA_OPTS ui.RewriteSources -m rewriteinfo_sig.txt -d rewrittenSources_sig -t "test.Seller,test.RList" unittests
scala $SCALA_OPTS ui.RewriteSources -v 3 --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies -t "test.Seller,test.RList" unittests

scala $SCALA_OPTS ui.InferStats --texout testtex/table.tex --json sig.json unittests:unitests
scala $SCALA_OPTS ui.InferStats --texout testtex/table.tex --bodies --json bod.json unittests:unitests
scala $SCALA_OPTS ui.TexTable testtex/table.tex sig.json bod.json
scala $SCALA_OPTS ui.RewriteAllSources -v 3 -m rewriteinfo_sig.txt -d rewrittenSources_sig unittests
scala $SCALA_OPTS ui.RewriteAllSources -v 3 --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies unittests

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
