FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/orderms-1.0.jar orderms.jar
ENTRYPOINT ["java", "-jar", "orderms.jar"]