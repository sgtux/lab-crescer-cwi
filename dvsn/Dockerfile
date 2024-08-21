FROM maven:3.8.5-openjdk-17 AS build
COPY /api/src /app/src
COPY /api/pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM maven:3.8.5-openjdk-17
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
COPY /api/src/main/resources/public/upload /upload
ENV TZ=America/Sao_Paulo
ENTRYPOINT ["java", "-jar", "/app.jar"]