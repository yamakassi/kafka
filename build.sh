cd KafkaConsumer &&
./gradlew bootJar &&
cd - &&
cd KafkaProducer
./gradlew bootJar &&
cd .. &&
docker compose up -d --build