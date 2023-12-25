# How to run a jar with different profile

java -jar -Dspring.profiles.active=dev authub-0.0.1.jar

# How to run unit tests only
``./mvnw test -Punit-tests``

# How to run integration tests in an isolated environment
``cd ../``

``cd infra && docker compose down && docker compose -f docker-compose.dev.yml up -d && cd ../authub && ./mvnw test``

# Issues running integration tests
[Flaky Test](https://www.jetbrains.com/teamcity/ci-cd-guide/concepts/flaky-tests/)