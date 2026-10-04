#! /bin/bash
# Downloads the Scala jars used to build and run VarJ from Maven Central.
# The jars are Scala 2.9.2 (the directory is named scala-2.9.3 for
# historical reasons) and are checked against the copies originally
# distributed with VarJ.
set -e
cd "$(dirname "$0")/scala-2.9.3"
mkdir -p lib
MAVEN=https://repo1.maven.org/maven2/org/scala-lang
fetch() {
  name=$1 sha=$2
  jar=lib/$name.jar
  if [ ! -f $jar ]; then
    echo "Downloading $jar"
    curl -fsSL -o $jar $MAVEN/$name/2.9.2/$name-2.9.2.jar
  fi
  echo "$sha  $jar" | shasum -a 256 -c -
}
fetch scala-library  cbbdb1e6bdf2f67ec5688a09a72a6d878183519cd68926553b8b86e918075f36
fetch scala-compiler 3067b59830c01c2ef8d27b8c872cd07229b13c28cefb3d56d82aacea65db540e
