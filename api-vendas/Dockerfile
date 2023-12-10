FROM openjdk:17-alpine3.13
LABEL authors="sebastiaofidencio"

RUN apk add --no-cache bash \
    && apk add --no-cache curl \
    && apk add --no-cache iputils


WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java","-Dspring.profiles.active=development", "-jar", "app.jar"]