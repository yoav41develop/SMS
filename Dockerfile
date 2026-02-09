# 1️⃣ Base image: Java 17 slim
FROM openjdk:17

# 2️⃣ Set working directory inside container
WORKDIR /app

# 3️⃣ Copy your Spring Boot JAR into the container
COPY target/myapp.jar /app/myapp.jar

# 4️⃣ Expose the port (optional, Render uses PORT env)
EXPOSE 8080

# 5️⃣ Set the command to run your JAR
CMD ["sh", "-c", "java -jar /app/myapp.jar"]
