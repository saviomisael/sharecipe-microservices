# How to run a jar with different profile

java -jar -Dspring.profiles.active=dev authub-0.0.1.jar

# How to run unit tests only
``./mvnw test -Punit-tests``

# How to run integration tests in an isolated environment
``cd ../``

``docker compose -f infra/docker-compose.dev.yml up -d && cd authub && ./mvnw test && cd ../infra/ && docker compose down``
