FROM amazoncorretto:21

ENV TZ=UTC

WORKDIR tastegateway

COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 3333

CMD ["java", "-Xmx250m", "-Dspring.profiles.active=dev", "-jar", "./target/tastegateway-0.0.1.jar"]