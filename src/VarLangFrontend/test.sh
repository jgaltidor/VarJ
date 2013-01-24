scala tame.Tester unittests/Lists.java
scala tame.Tester unittests
scala tame.Tester --verbose 3 unittests
scala tame.LookupVar --generic java.util.List unittests/Lists.java
scala tame.LookupVar --generic java.util.List unittests
scala tame.LookupVar --generic java.util.List --verbose 3 unittests
scala tame.InferStats --texout testtex/table.tex  unittests/Lists.java:lists unittests/PLDITest.java:pldi
scala tame.AnalyzeType --type test.Animal unittests
scala tame.AnalyzeType --type test.IList unittests
scala tame.RewriteSources -m rewrites.txt -d rewrittenSources -t test.RewriteTest unittests

