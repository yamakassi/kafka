FROM openjdk:17 as myapp
VOLUME /tmp
COPY  build/libs/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]