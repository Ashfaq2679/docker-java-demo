docker stop java-app-container mongo-container
docker rm java-app-container mongo-container
./mvnw clean package -DskipTests



