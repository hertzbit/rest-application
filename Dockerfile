FROM adoptopenjdk/openjdk11:latest

COPY target/rest-application-0.0.1-SNAPSHOT.jar rest-application.jar

ENTRYPOINT ["java", "-jar", "/rest-application.jar"]
