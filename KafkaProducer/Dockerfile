FROM openjdk:17 as myapp2
VOLUME /KafkaProducer
COPY  /build/libs/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]