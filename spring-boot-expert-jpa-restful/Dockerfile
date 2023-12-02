FROM openjdk:17-alpine3.13
LABEL authors="sebastiaofidencio"

RUN apk add --no-cache bash \
    && apk add --no-cache curl \
    && apk add --no-cache iputils


WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java","-Dspring.profiles.active=production", "-jar", "app.jar"]

#https://tpbabparn.medium.com/spring-boot-3-1-integrated-docker-compose-on-development-environment-spring-webflux-example-1ddcbfe052f
#https://springhow.com/spring-boot-and-postgres-using-docker-compose/


#ENTRYPOINT ["top", "-b"]