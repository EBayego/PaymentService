# Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/paymentservice.jar paymentservice.jar
ENTRYPOINT ["java","-jar","/paymentservice.jar"]