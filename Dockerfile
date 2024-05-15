FROM eclipse-temurin:22-jre-alpine

WORKDIR /app

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]