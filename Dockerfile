FROM openjdk:8-jdk-alpine
MAINTAINER mwaqaspervez
COPY target/assessment-0.0.1-SNAPSHOT.jar assessment-0.0.1-SNAPSHOT-1.0.0.jar
ENTRYPOINT ["java","-jar","/assessment-0.0.1-SNAPSHOT.jar"]
