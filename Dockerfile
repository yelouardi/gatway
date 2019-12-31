FROM openjdk:11.0.5-jre-slim

EXPOSE 8762

ADD /target/gatway-0.0.1-SNAPSHOT.jar gatway-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","gatway-0.0.1-SNAPSHOT.jar"]