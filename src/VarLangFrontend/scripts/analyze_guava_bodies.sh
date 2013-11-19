source ./scripts/setenv.sh
set -ex
java $JAVA_OPTS ui.Tester --bodies -j -classpath -j $LIBCP $GUAVA/src
# java $JAVA_OPTS ui.InferStats --bodies -j -classpath -j $LIBCP --texout testtex/table.tex $GUAVA/src:Guava
