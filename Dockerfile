FROM openjdk:11.0.5-jre-slim

COPY ./target/gatway-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch gatway-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","gatway-0.0.1-SNAPSHOT.jar"]