FROM openjdk:11

VOLUME "/desafio"

EXPOSE 8080

ADD ./target/address-api-0.0.1-SNAPSHOT.jar address-stoom-api.jar

ENTRYPOINT [ "java", "-jar", "/address-stoom-api.jar"]
