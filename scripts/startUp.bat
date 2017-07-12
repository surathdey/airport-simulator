@ECHO ON
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;../config
set CLASSPATH=%CLASSPATH%;../lib/*

set JAVA_OPTS=-Xms512m -Xmx1g -Denv=LOCAL -Dlog.dir=../log
set MAIN_CLASS=com.loyaltyone.problem.airportsim.Main

java  %JAVA_OPTS% %MAIN_CLASS%