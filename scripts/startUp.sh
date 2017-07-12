CP=.
CP=$CP:../config
CP=$CP:../lib/*

JAVA_OPTS="-Xms512m -Xmx1g -Denv=LOCAL -Dlog.dir=../log"
MAIN_CLASS="com.loyaltyone.problem.airportsim.Main"

java $JAVA_OPTS -classpath $CP $MAIN_CLASS