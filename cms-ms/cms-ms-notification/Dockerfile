FROM openjdk:8-jdk-alpine
LABEL maintainer="zhangyaohui<zhangyaohui@yunjie-system.com>"
COPY target/*.jar /app.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx200m", "-jar", "/app.jar"]
EXPOSE 9130