FROM adoptopenjdk/openjdk11:latest

COPY target/*.jar rest-application.jar
EXPOSE 8088
CMD ["java", "-jar", "rest-application.jar"]
