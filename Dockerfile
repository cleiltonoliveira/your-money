FROM adoptopenjdk/openjdk11:latest
RUN mkdir -p /software
ADD target/application-0.0.1-SNAPSHOT.jar /software/application-0.0.1-SNAPSHOT.jar
CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /software/application-0.0.1-SNAPSHOT.jar
