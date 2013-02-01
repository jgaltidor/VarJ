scala ui.Tester unittests/Lists.java
scala ui.Tester unittests
scala ui.Tester --verbose 3 unittests
scala ui.LookupVar --generic java.util.List unittests/Lists.java
scala ui.LookupVar --generic java.util.List unittests
scala ui.LookupVar --generic java.util.List --verbose 3 unittests
scala ui.InferStats --texout testtex/table.tex  unittests/Lists.java:lists unittests/PLDITest.java:pldi
scala ui.AnalyzeType --type test.Animal unittests
scala ui.AnalyzeType --type test.IList unittests
scala ui.RewriteSources -m rewriteinfo.txt -d rewrittenSources -t test.Seller unittests

