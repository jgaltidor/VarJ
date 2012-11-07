scala tame.Tester unittests/AnotherIList.java
scala tame.Tester unittests
scala tame.Tester -verbosity 3 unittests
scala tame.LookupVar -generic java.util.List unittests/AnotherIList.java
scala tame.LookupVar -generic java.util.List unittests
scala tame.LookupVar -generic java.util.List -verbosity 3 unittests
scala tame.InferStats -texout tmp.tex  unittests/RList.java:rlib unittests/IList.java:IList
scala tame.AnalyzeType -type test.Animal unittests
scala tame.AnalyzeType -type test.IList unittests
