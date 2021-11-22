# PRFT COEX Java practice

A Spring Boot API was created from scratch to manage and simulate a school/university system where exists Students, Teachers, and Groups. In this case, the project was built using

## Technologies

- [Java v11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Gradle](https://gradle.org/)
- [Spring boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.school.SchoolApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.gradle)

```shell
./gradlew build

./gradlew bootRun
```

### Using docker

Other way to run the application is using docker in this case we run  the folloeing commands and a docker image will be create and tha app will run over a docker environmnent

```shell
docker-compose up
```

##Database Model

![DatabaseModel](https://user-images.githubusercontent.com/47896664/142931256-b6498aea-e002-4430-a81d-47ced1ddf3fa.png)



