FROM openjdk:21

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#EXPOSE 8082
#
#CMD ["./mvnw", "spring-boot:run"]