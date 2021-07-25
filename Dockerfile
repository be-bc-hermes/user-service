FROM adoptopenjdk/openjdk11:latest
EXPOSE 8085
ARG JAR_FILE=target/hedwig-user-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} user-service-app.jar
ENTRYPOINT ["java", "-jar", "user-service-app.jar"]