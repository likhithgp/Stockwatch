FROM openjdk:17

# ADD target/stockwatch-0.0.1-SNAPSHOT.jar /usr/src/stockwatch-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY target/stockwatch-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar", "app.jar"]