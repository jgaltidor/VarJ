#! /bin/bash
source ./scripts/setenv.sh

set -ex
java $JAVA_OPTS ui.InferStats --bodies --texout testtex/table.tex $APACHE/src/java:Apache
