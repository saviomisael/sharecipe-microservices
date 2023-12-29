package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.SignInCredentialsDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.And
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
  fun `A chef attempts to log in with a username that is fewer than 2 characters`() {
    username = "a"
  }

  @Given("A chef attempts to log in with a username that exceeds 255 characters")
  fun `A chef attempts to log in with a username that exceeds 255 characters`() {
    username = "a".repeat(256)
  }

  @Given("A chef attempts to log in with a password that is fewer than 8 characters")
  fun `A chef attempts to log in with a password that is fewer than 8 characters`() {
    password = "123"
  }

  @Given("A chef attempts to log in with a password that exceeds 255 characters")
  fun `A chef attempts to log in with a password that exceeds 255 characters`() {
    password = "a".repeat(256)
  }

  @Given("A chef attempts to log in with a password without any uppercase letter")
  fun `A chef attempts to log in with a password without any uppercase letter`() {
    password = "test123@"
  }

  @Given("A chef attempts to log in with a password without any lowercase letter")
  fun `A chef attempts to log in with a password without any lowercase letter`() {
    password = "TEST123@"
  }

  @Given("A chef attempts to log in with a password without any number")
  fun `A chef attempts to log in with a password without any number`() {
    password = "TESTEST@"
  }

  @Given("A chef attempts to log in with a password without any symbol")
  fun `A chef attempts to log in with a password without any symbol`() {
    password = "TestTest123"
  }

  @Given("A chef attempts to log in with an username that does not exist")
  fun `A chef attempts to log in with an username that does not exist`() {
    username = "unknown"
    password = "Test123@"
  }

  @Given("A chef already have an account")
  fun `A chef attempts to log in with a wrong password`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(objectMapper.writeValueAsString(SignInCredentialsDto("user for sign in", "@Test123")))
      .`when`()
      .post(ApiRoutes.ChefRoutes.signIn)
      .then()
  }

  @And("Attempts to log in with a wrong password")
  fun `Attempts to log in with a wrong password`() {
    username = "user for sign in"
    password = "Test123@"
  }

  @When("This chef tries to log in")
  fun `This chef tries to log in`() {
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
  fun `Returns a bad request`() {
    performRequest.log().all().statusCode(HttpStatus.BAD_REQUEST.value()).contentType(ContentType.JSON)
  }

  @Then("Returns unauthorized")
  fun `Returns unauthorized`() {
    performRequest.log().all().statusCode(HttpStatus.UNAUTHORIZED.value()).contentType(ContentType.JSON)
  }
}