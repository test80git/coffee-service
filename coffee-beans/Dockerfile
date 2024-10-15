FROM openjdk:17-alpine3.14
WORKDIR ./app
COPY ./build/libs/coffee-beans-service-0.0.1-SNAPSHOT.jar ./app/beans-service-app.jar
ENTRYPOINT [ "java", "-jar", "./app/grain-service-app.jar"]