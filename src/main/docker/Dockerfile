FROM 127.0.0.1:5000/java:8-jre

ENV JHIPSTER_SLEEP 0

# add directly the war
ADD oauth2-demo.jar /app.jar
#ADD resources /resources

RUN sh -c 'touch /app.jar'
VOLUME /tmp
EXPOSE 8082
CMD echo "oauth2-demo will start in ${JHIPSTER_SLEEP}s..." && \
    sleep ${JHIPSTER_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar ${JAVA_OPTS} /app.jar