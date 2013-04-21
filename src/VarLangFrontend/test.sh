set -ex

scala ui.Tester unittests/Lists.java
scala ui.Tester unittests
scala ui.Tester --bodies unittests
scala ui.LookupVar --generic test.WList unittests/Lists.java
scala ui.LookupVar --generic test.WList -v 3 unittests/Lists.java
scala ui.InferStats --texout testtex/table.tex  unittests/Lists.java:lists unittests/PLDITest.java:pldi
scala ui.AnalyzeType --type test.Animal unittests
scala ui.AnalyzeType --type test.IList unittests
scala ui.RewriteSources -m rewriteinfo_sig.txt -d rewrittenSources_sig -t "test.Seller,test.RList" unittests
scala ui.RewriteAllSources --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies unittests

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
