FROM openjdk:17
EXPOSE 8080
ADD target/rest_api.jar rest_api.jar
ENTRYPOINT ["java", "-jar", "/rest_api.jar"]