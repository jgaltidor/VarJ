source ./scripts/setenv.sh
set -ex
# Analyze JPaul Type
echo Analyzing JPaul Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP -t $1 $JPAUL/src
