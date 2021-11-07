FROM openjdk:11
EXPOSE 8080
COPY build/libs/school-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
