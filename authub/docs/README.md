# How to run a jar with different profile

java -jar -Dspring.profiles.active=dev authub-0.0.1.jar

# How to run unit tests only

``./mvnw test -Punit-tests``

# How to run integration tests in an isolated environment

``cd ../``

``docker system prune -f && docker compose -f docker-compose.qa.yml down && docker compose -f docker-compose.qa.yml build --no-cache && docker compose -f docker-compose.qa.yml up -d && ./mvnw clean test``

# Issues about testing

[Flaky Test](https://www.jetbrains.com/teamcity/ci-cd-guide/concepts/flaky-tests/)

## Unit tests = White-box testing & Cucumber tests = Black-box testing?
[Differences between Black Box Testing vs White Box Testing](https://www.geeksforgeeks.org/differences-between-black-box-testing-vs-white-box-testing/)

[Is there a difference between white box testing and unit testing?](https://www.onpathtesting.com/blog/difference-between-white-box-testing-and-unit-testing)