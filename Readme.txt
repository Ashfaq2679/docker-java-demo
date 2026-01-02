1. Create spring boot project with only starter-web and spring-data-mongodb dependency.
2. set the mongodb connection in application.yml or properties  as below:
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/testdb

3. Run docker command to start mongodb container:
docker run -d --name mongo-container -p 27017:27017 mongo:6
   This command will pull the mongo:6 image from docker hub and start a container named mongo-container mapping the host port 27017 to container port 27017.
   Since port is mapped, you can connect to mongodb running inside the container using localhost:27017 from your spring boot application running on localhost.
4. Start the spring boot application and test the GET and POST APIs using postman or curl commands.
   At this point spring boot application is running on localhost:9090 and mongodb is running inside the docker container on localhost:27017 
   and both are able to communicate with each other.
5. To take spring boot application also to docker container, create a Dockerfile in the root of the project with below content:
   
   FROM openjdk:17-jdk-alpine
   WORKDIR /app
   COPY ./target/docker-demo.jar app.jar
   EXPOSE 9090
   ENTRYPOINT ["java","-jar","app.jar"]
   
6. change the uri of mongodb in application.yml or properties to point to the mongo-container as below:
spring:
  data:
    mongodb:
      uri: mongodb://mongo-container:27017/testdb
      
7. Build the spring boot application jar using maven command:
	mvn clean package
8. Build the docker image for spring boot application using below command:
   docker build -t docker-java-demo .
9. Run the spring boot application docker container using below command:
   docker run -d --name spring-boot-container -p 9090:9090 --link mongo-container:mongo-container docker-java-demo
   
   This command will start the spring boot application container named spring-boot-container mapping host port 9090 to container port 9090 
   and linking it to mongo-container so that it can access mongodb running inside mongo-container.
10. Now you can access the spring boot application APIs using localhost:9090 from your host machine and it will be able to communicate with mongodb running inside mongo-container.


   NOTE: In case of any application failure, you can check the logs of the spring-boot-container using below command:
   docker logs spring-boot-container
   
11. To stop and remove the containers, you can use below commands:
   docker stop spring-boot-container mongo-container
   docker rm spring-boot-container mongo-container
   
   To list the network created by docker for the containers, you can use below command:
   docker network ls
   and to delete the network if needed, you can use:
   docker network rm <network-id>
   
   12. To clean up the docker images created, you can use below commands:
   docker rmi docker-java-demo mongo:6

# Docker compose (Optional)
Docker Compose is a tool that allows you to define and manage multi-container Docker applications using a single YAML file.
1. Create a docker-compose.yml file in the root of your project with the following content:
version: '3.8'
services:
  mongo-container:
    image: mongo:6
    container_name: mongo-container
    ports:
      - "27017:27017"
  spring-boot-container:
    image: docker-java-demo
    container_name: spring-boot-container
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "9090:9090"
    depends_on:
      - mongo-container
      