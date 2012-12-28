scala tame.Tester unittests/AnotherIList.java
scala tame.Tester unittests
scala tame.Tester --verbose 3 unittests
scala tame.LookupVar --generic java.util.List unittests/AnotherIList.java
scala tame.LookupVar --generic java.util.List unittests
scala tame.LookupVar --generic java.util.List --verbose 3 unittests
scala tame.InferStats --texout testtex/table.tex  unittests/RList.java:rlib unittests/IList.java:IList
scala tame.AnalyzeType --type test.Animal unittests
scala tame.AnalyzeType --type test.IList unittests
