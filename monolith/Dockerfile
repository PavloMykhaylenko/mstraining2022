FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/monolith-1.0.jar mono.jar
ENTRYPOINT ["java", "-jar", "mono.jar"]