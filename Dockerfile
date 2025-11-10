# syntax=docker/dockerfile:1.6

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace/app
COPY pom.xml ./
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/app/target/quarkus-app/lib/ ./lib/
COPY --from=build /workspace/app/target/quarkus-app/app/ ./app/
COPY --from=build /workspace/app/target/quarkus-app/quarkus/ ./quarkus/
COPY --from=build /workspace/app/target/quarkus-app/quarkus-run.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]
