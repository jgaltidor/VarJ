# TOOLDIR=../JastAddJ/Java1.4Frontend/tools
# java -jar $TOOLDIR/jastadd2.jar --package=AST *.ast *.jrag
export ANT_OPTS=-Xmx256m
ant gen
