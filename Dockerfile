FROM openjdk:17-alpine
ADD target/loginAndRegistration-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar loginAndRegistration-0.0.1-SNAPSHOT.jar