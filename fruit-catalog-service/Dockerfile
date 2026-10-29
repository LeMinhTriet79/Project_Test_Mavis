FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/fruit-catalog-service-1.0.0.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
