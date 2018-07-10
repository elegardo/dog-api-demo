FROM openjdk:10-jre-slim

ADD maven/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]