source ./scripts/setenv.sh
set -ex
# Analyze JScience Type
echo Analyzing JScience Type $1
java $JAVA_OPTS ui.AnalyzeType -j -classpath -j $LIBCP -v 3 --bodies -t $1 $JSCIENCE/src
