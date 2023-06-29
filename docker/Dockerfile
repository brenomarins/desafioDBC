FROM azul/zulu-openjdk-alpine:11

USER root

EXPOSE 8080

COPY target/BrenoDBC-0.0.1-SNAPSHOT.jar BrenoDBC.jar

ENTRYPOINT ["java","-jar","BrenoDBC.jar"]