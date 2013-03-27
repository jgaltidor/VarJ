set -ex

scala ui.Tester unittests/Lists.java
scala ui.Tester unittests
scala ui.Tester -v 3 unittests
scala ui.LookupVar --generic test.WList unittests/Lists.java
scala ui.LookupVar --generic test.WList -v 3 unittests/Lists.java
scala ui.LookupVar --bodies --generic MBodyTest -v 3 unittests/MBodyTest.java
scala ui.InferStats --texout testtex/table.tex  unittests/Lists.java:lists unittests/PLDITest.java:pldi
scala ui.AnalyzeType --type test.Animal unittests
scala ui.AnalyzeType --type test.IList unittests
scala ui.RewriteSources -m rewriteinfo_sig.txt -d rewrittenSources_sig -t "test.Seller,test.Lists,MBodyTest" unittests
scala ui.RewriteSources --bodies -m rewriteinfo_bodies.txt -d rewrittenSources_bodies -t "test.Seller,test.Lists,MBodyTest" unittests

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
