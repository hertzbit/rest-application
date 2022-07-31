FROM java:11

COPY target/rest-application-* rest-application.jar

ENTRYPOINT ["java", "-jar", "rest-application.jar"]
