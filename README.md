# Assessment test

This project contains the assessment test created by Muhammad Waqas
Java version used to develop this project is Java 14.

## To build with docker

Use

```
docker build --tag=application:latest .
docker run -p8081:8082 application:latest
```

## Build & Run with Maven

You can use the below command to compile the project

```
mvn compile
```

To run tests you can use the below command

```
mvn test
```

Run the spring boot application:

```
mvn spring-boot:run
```

To package the application,The jar file will be available at ```PROJECT_DIR/target```

```
mvn package
```