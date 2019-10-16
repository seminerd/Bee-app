#!/bin/sh
# echo $SPRING_PROFILES_ACTIVE
export HOST_NAME=`cat /etc/hostname`
exec java $@ $JAVA_OPTS \
  -jar /beemart-service.jar