FROM gradle:6-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/school-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar",  "/app.jar"]