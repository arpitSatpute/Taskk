FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package

CMD ["java", "-jar", "target/taskk.jar"]
CMD ["./mvnw", "spring-boot:run"]