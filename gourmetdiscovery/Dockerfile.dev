FROM amazoncorretto:21

ENV TZ=UTC

WORKDIR gourmetdiscovery

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8761

CMD ["java", "-Dspring.profiles.active=dev", "-jar", "./target/gourmetdiscovery-1.0.0.jar"]