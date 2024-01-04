# How to run a jar with different profile

java -jar -Dspring.profiles.active=dev authub-0.0.1.jar

# How to run unit tests only

``./mvnw test -Punit-tests``

# How to run integration tests in an isolated environment

``cd ../``

``docker system prune -f && docker compose -f docker-compose.qa.yml down && docker compose -f docker-compose.qa.yml build --no-cache && docker compose -f docker-compose.qa.yml up -d && ./mvnw clean test``

# Authub Issues

[Flaky Test](https://www.jetbrains.com/teamcity/ci-cd-guide/concepts/flaky-tests/)

## Unit tests = White-box testing & Cucumber tests = Black-box testing?

[Differences between Black Box Testing vs White Box Testing](https://www.geeksforgeeks.org/differences-between-black-box-testing-vs-white-box-testing/)

[Is there a difference between white box testing and unit testing?](https://www.onpathtesting.com/blog/difference-between-white-box-testing-and-unit-testing)

[Integration testing: Is it black box or white box testing?](https://www.techtarget.com/searchsoftwarequality/answer/Integration-testing-Is-it-black-box-or-white-box-testing#:~:text=Integration%20testing%20can%20be%20either,the%20system%20they%20are%20testing.)

## Cucumber API

### @BeforeStep

If you want to run something before a step, this annotation runs this method in this way, but if you use, for
example in SignInSteps, this will run before the steps from CreateChefAccountSteps.

```kotlin
//  This runs before all steps in any feature file
@BeforeStep
fun beforeStep(scenario: Scenario) {
}
```

### Executing features in parallel

[By default, when parallel execution is enabled, scenarios and examples are executed in parallel. Due to limitations,
JUnit 4 could only execute features in parallel. This behaviour can be restored by setting the configuration parameter
cucumber.execution.execution-mode.feature to same_thread.](https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#executing-features-in-parallel)

## ContentCachingRequestWrapper (ChatGPT answer)

`ContentCachingRequestWrapper` is a class provided by Spring Framework that extends the standard
`HttpServletRequestWrapper`. It is part of Spring's support for request and response content caching.

This wrapper allows the content of the original request's body to be read multiple times. Normally, once the input
stream of the request body is consumed, it cannot be read again. However, ContentCachingRequestWrapper captures the
request body content and provides methods to access it multiple times.

Here are some key points about ContentCachingRequestWrapper:

Content Caching:

When you wrap an incoming HttpServletRequest with ContentCachingRequestWrapper, it captures the input stream of the
request body and caches its content.
This allows you to read the request body multiple times, which can be useful in certain scenarios, such as logging,
debugging, or processing the request body multiple times.