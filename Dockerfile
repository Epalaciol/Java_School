FROM openjdk:12-jdk-alpine
EXPOSE 8080
COPY build/libs/*.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
