FROM elegardo/java-10:v0.1

ADD maven/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]