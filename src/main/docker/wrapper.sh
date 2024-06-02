#!/usr/bin/env bash
if [ "$1" == "TRUE" ]; then
	exec java -javaagent:$JAVA_AGENT_PATH -Djava.security.egd=file:/dev/./urandom $BOOTAPP_JAVA_OPTS -classpath "$CLASSPATH" com.casa.post_service.PostServiceApplication -Dspring.profiles.active=$ENV_NAME  > /dev/stdout 2>&1
else
	exec java -javaagent:$JAVA_AGENT_PATH -Djava.security.egd=file:/dev/./urandom $BOOTAPP_JAVA_OPTS -classpath "$CLASSPATH" com.casa.post_service.PostServiceApplication -Dspring.profiles.active=$ENV_NAME  > /dev/stdout 2>&1;
fi