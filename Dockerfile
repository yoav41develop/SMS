FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY target/myapp.jar app.jar

CMD ["java", "-jar", "app.jar"]
