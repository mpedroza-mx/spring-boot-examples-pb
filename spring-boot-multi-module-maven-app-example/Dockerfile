# === Stage 1: Build package ===
FROM maven:3-eclipse-temurin-21 as build

WORKDIR /app

COPY spring-boot-rest-service/pom.xml spring-boot-rest-service/pom.xml
COPY module-1/pom.xml module-1/pom.xml
COPY module-2/pom.xml module-2/pom.xml
COPY pom.xml .

COPY spring-boot-rest-service/src ./spring-boot-rest-service/src
COPY module-1/src ./module-1/src
COPY module-2/src ./module-2/src

#Build the application
RUN  --mount=type=cache,target=/root/.m2 mvn clean package -B

#Normal build process
#RUN mvn clean package -B

#Adding 1 thread per core
#RUN mvn clean package -B -T 1C

# === Stage 2: Create the runtime image ===
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=build /app/spring-boot-rest-service/target/*.jar app.jar

RUN useradd -m springuser && chown -R springuser /app
USER springuser
ENTRYPOINT ["java", "-jar", "app.jar"]