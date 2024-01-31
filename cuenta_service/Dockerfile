FROM amazoncorretto:17
LABEL authors="eider"
VOLUME /tmp
EXPOSE 8098
ARG JAR_FILE=target/cuenta_service_devsu-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
