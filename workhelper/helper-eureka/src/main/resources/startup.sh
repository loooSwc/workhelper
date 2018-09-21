#!/bin/sh
export CLASSPATH=.
for i in lib/*.jar;
do CLASSPATH=./$i:"$CLASSPATH";
cat  $CLASSPATH;
done

CLASSPATH=\
.:\
./conf:\
$CLASSPATH:\
helper-eureka-1.0-SNAPSHOT.jar

export CLASSPATH
java -server com.workhelper.EurekaServerApplication
