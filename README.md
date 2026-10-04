Java Generics Refactoring Tool for Inferring Wildcards
=======================================================

This software package contains the Java refactoring tool
presented in the [OOPSLA 2014][ooplsa14] conference paper,
[Refactoring Java Generics by Inferring Wildcards,
In Practice][ooplsa14_paper].
Instructions for using this package are given in
file [`artifact_overview.pdf`](artifact_overview.pdf).

[ooplsa14]: http://2014.splashcon.org/track/oopsla2014
[ooplsa14_paper]: http://dl.acm.org/citation.cfm?doid=2660193.2660203

Setup
-----

Some libraries that were bundled with the original artifact are no
longer included in this repository:

* **Scala 2.9.2** (used to build and run VarJ). Run
  `src/3rd_party_libs/fetch-deps.sh` to download it from Maven Central.
* **JDK 1.6.0_06 source code** (analyzed as the "Java" library in the
  paper). Its license does not permit redistribution. To reproduce that
  analysis, place the source in `analyzed_libs/jdk1.6.0_06_src/`.
  The OpenJDK 6 source is an open-source alternative, though results
  may differ slightly from those in the paper.
* **Java 5 class library** (`classes.jar`, needed on the classpath when
  analyzing Guava). Set the `JAVA5_CLASSES` environment variable to the
  `rt.jar` (or `classes.jar`) of a Java 5 or 6 runtime.
