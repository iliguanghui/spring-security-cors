FROM maven:3-eclipse-temurin-21 AS builder
COPY settings.xml /usr/share/maven/conf/settings.xml
WORKDIR /src
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -Dmaven.test.skip=true
FROM eclipse-temurin:21-jdk-jammy
RUN sed -i 's@http://archive.ubuntu.com@https://mirrors.aliyun.com@g' /etc/apt/sources.list && \
    sed -i 's@http://security.ubuntu.com@https://mirrors.aliyun.com@g' /etc/apt/sources.list
WORKDIR /app
COPY --from=builder /src/target/*.jar ./app.jar
EXPOSE 8080/tcp
CMD ["/bin/sh", "-c", "exec java -jar app.jar"]
