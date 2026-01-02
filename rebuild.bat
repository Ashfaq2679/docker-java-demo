echo Building docker image for the project, image name: spring-mongo-app
docker build --no-cache -t spring-mongo-app .
echo deleting the network, network name: java-mongo-network
docker network rm java-mongo-network
echo creating the network again.
docker network create java-mongo-network
echo running mongo:6 image to create a container named mongo-container on java-mongo-network
docker run -d -p 27017:27017 --name mongo-container --network java-mongo-network mongo:6
echo running spring-mongo-app:latest image to create a container named java-app-container on java-mongo-network
docker run -d --name java-app-container --network java-mongo-network -p 9090:9090 spring-mongo-app
docker logs java-app-container