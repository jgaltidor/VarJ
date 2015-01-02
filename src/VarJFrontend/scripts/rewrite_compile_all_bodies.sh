#! /bin/bash
set -x

libs='trove jscience jpaul java guava apache'
for lib in $libs
do
  ./scripts/rewrite_compile_${lib}_bodies.sh &> ${lib}_bodies_out.txt
  grep -v '\-1' rewriteinfo.txt > ${lib}_rewrites_bodies.txt
  # rm -rf rewrittenSources rewriteinfo.txt
done
