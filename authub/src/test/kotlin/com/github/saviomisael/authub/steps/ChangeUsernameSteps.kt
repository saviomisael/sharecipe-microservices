package com.github.saviomisael.authub.steps

import com.github.saviomisael.authub.adapter.presentation.dto.ChangeUsernameDto
import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.springframework.http.HttpStatus
import java.util.*

class ChangeUsernameSteps {
  private var token = ""
  private var username = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef does not have a token")
  fun `A chef does not have a token`() {
    token = ""
  }

  @Given("A chef is logged-in in the system")
  fun `A chef is logged-in in the system`() {
    username = "torvalds-${UUID.randomUUID()}"

    token = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("Torvalds", username, "@Test123", "torvalds-${UUID.randomUUID()}@email.com"))
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
      .log()
      .all()
      .extract()
      .response()
      .jsonPath()
      .getString("data.token")
  }

  @And("Wants to change his username with a username less than 2 characters")
  fun `Wants to change his username with a username less than 2 characters`() {
    username = "a"
  }

  @And("Wants to change his username with a username longer than 255 characters")
  fun `Wants to change his username with a username longer than 255 characters`() {
    username = "a".repeat(256)
  }

  @When("He tries to change his username")
  fun `He tries to log in`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $token")
      .body(ChangeUsernameDto(username))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
  }

  @Then("Returns unauthorized because he does not have a token")
  fun `Returns unauthorized because he does not have a token`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNAUTHORIZED.value())
  }

  @Then("Returns a bad request because new username is invalid")
  fun `Returns a bad request because new username is invalid`() {
    performRequest
      .log()
      .all()
      .statusCode(HttpStatus.BAD_REQUEST.value())
      .contentType(ContentType.JSON)
  }
}