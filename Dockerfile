FROM maven:3.8.5-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN ["mvn", "clean", "install"]
