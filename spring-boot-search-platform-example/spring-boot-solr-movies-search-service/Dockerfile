# === Stage 1: Build package ===
FROM maven:3-eclipse-temurin-21 as build

WORKDIR /app

COPY pom.xml .

COPY src ./src

#Build the application and skip tests for speed
RUN  --mount=type=cache,target=/root/.m2 mvn clean package -B -DskipTests

# === Stage 2: Create the runtime image ===
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

RUN useradd -m springuser && chown -R springuser /app
USER springuser
ENTRYPOINT ["java", "-jar", "app.jar"]