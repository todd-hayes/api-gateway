FROM arm64v8/amazoncorretto:21-al2023-jdk
MAINTAINER toddhayes
COPY build/libs/api-gateway-1.0.0-SNAPSHOT.jar /opt/app/
EXPOSE 8080
CMD ["java", "-showversion", "-jar", "/opt/app/api-gateway-1.0.0-SNAPSHOT.jar"]