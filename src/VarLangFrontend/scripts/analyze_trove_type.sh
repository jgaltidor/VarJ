source ./scripts/setenv.sh
set -ex
# Analyze Trove Type
echo Analyzing Trove Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP -t $1  $TROVE/src
