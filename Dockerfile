FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY ./target/docker-demo.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]