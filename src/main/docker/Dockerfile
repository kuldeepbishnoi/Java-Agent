FROM amazoncorretto:17-alpine-jdk

RUN apk add --no-cache bash

ENV ENV_NAME local
ENV DD_OPTS ""
ENV BOOTAPP_JAVA_OPTS -Xms1200m
ENV BOOTAPP_JAVA_OPTS -Xmx1200m
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.text=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.net=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.util.concurrent=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.util=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.math=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.lang=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.time=ALL-UNNAMED
ENV BOOTAPP_JAVA_OPTS --add-opens=java.base/java.time.LocalDateTime=ALL-UNNAMED
ENV CLASSPATH=/app/classes:/app/lib/*

ENV BOOTAPP_USR="root"
ENV BOOTAPP_GROUP="root"
ENV BOOTAPP_PATH="/app.jar"
ENV JAVA_AGENT_PATH="/javaagent.jar"
ENV SERVER_PORT 8000
ENV DB_HOST=host.docker.internal
ENV DB_PORT=3306
ENV DB_NAME=post_database
ENV DB_USER=root
ENV DB_PASSWORD=rootroot
ENV HT_MODE=RECORD
EXPOSE $SERVER_PORT

COPY wrapper.sh /wrapper.sh

RUN chmod 555 /wrapper.sh

USER root

COPY post-service-LATEST.jar $BOOTAPP_PATH
COPY agent-service-LATEST.jar $JAVA_AGENT_PATH
COPY lib /app/lib
COPY target/classes /app/classes

RUN chmod 555 $BOOTAPP_PATH && \
            touch $BOOTAPP_PATH

USER $BOOTAPP_USR
ENTRYPOINT ["/wrapper.sh"]
