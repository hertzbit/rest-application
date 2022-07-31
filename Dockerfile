FROM adoptopenjdk/openjdk11:latest

COPY target/rest-application-* rest-application.jar

ENTRYPOINT ["java", "-jar", "/rest-application.jar"]
