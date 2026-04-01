FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build

# copy pom and sources for a reproducible build
COPY pom.xml ./
COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# copy the built jar from the builder stage
ARG JAR_FILE=target/*.jar
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
