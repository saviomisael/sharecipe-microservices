package com.github.saviomisael.authub.steps

import com.github.saviomisael.authub.adapter.presentation.dto.ChangeUsernameDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.springframework.http.HttpStatus

class ChangeUsernameSteps {
  private var token = ""
  private var username = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef does not have a token")
  fun `A chef does not have a token`() {
    token = ""
  }

  @When("He tries to log in")
  fun `He tries to log in`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .header("Authorization", "Bearer $token")
      .body(ChangeUsernameDto(username))
      .`when`()
      .post(ApiRoutes.ChefRoutes.changeUsername)
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
}