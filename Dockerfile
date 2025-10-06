FROM openjdk:26-jdk
WORKDIR /app
COPY UserService/target/UserService-0.0.1-SNAPSHOT.jar /UserService.jar
ENV SERVER_PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/UserService.jar","-server.port=${SERVER_PORT}"]
