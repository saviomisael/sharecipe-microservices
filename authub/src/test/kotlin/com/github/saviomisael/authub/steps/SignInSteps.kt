package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.SignInCredentialsDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
  properties = ["spring.profiles.active=qa"]
)
class SignInSteps {
  private val objectMapper = ObjectMapper()
  private var username = ""
  private var password = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef attempts to log in with a username that is fewer than 2 characters")
  fun a_chef_attempts_to_log_in_with_a_username_that_is_fewer_than_2_characters() {
    username = "a"
  }

  @Given("A chef attempts to log in with a username that exceeds 255 characters")
  fun a_chef_attempts_to_log_in_with_a_username_that_exceeds_255_characters() {
    username = "a".repeat(256)
  }

  @When("This chef tries to log in")
  fun this_chef_tries_to_log_in() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(objectMapper.writeValueAsString(SignInCredentialsDto(username, password)))
      .`when`()
      .post(ApiRoutes.ChefRoutes.signIn)
      .then()
  }

  @Then("Returns a bad request")
  fun returns_a_bad_request() {
    performRequest.log().all().statusCode(HttpStatus.BAD_REQUEST.value()).contentType(ContentType.JSON)
  }
}