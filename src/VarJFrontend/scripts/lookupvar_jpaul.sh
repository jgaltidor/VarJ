#! /bin/bash
source ./scripts/setenv.sh
set -ex
# Lookup Var
echo Lookup Variance of type JPaul Type $1
java $JAVA_OPTS ui.LookupVar -j -classpath -j $LIBCP -g $1 $JPAUL/src
