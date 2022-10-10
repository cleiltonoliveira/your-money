FROM adoptopenjdk/openjdk11:latest
RUN mkdir -p /software
ADD target/x-x-1.1.1.jar /software/x-x-1.1.1.jar
CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /software/x-x-1.1.1.jar