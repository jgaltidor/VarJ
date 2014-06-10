#! /bin/bash
source ./scripts/setenv.sh

set -ex
java $JAVA_OPTS ui.InferStats --texout testtex/table.tex $APACHE/src/java:Apache
