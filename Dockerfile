FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
ADD target/portal-api.war portal-api.war
ENTRYPOINT ["java", "-jar", "/app.war"]
EXPOSE 8080