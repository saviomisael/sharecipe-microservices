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

  @Given("A chef that wants to log in")
  fun a_chef_that_wants_to_log_in() {
    username = "a"
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