package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.ChangePasswordDto
import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.assertj.core.api.Assertions
import org.springframework.http.HttpStatus

class ChangePasswordSteps {
  private val objectMapper = ObjectMapper()
  private var token = ""
  private var newPassword = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A user without a token")
  fun `A user without a token`() {
    token = ""
  }

  @Given("A chef that creates his account")
  fun `A chef that creates his account`() {
    token = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(
        objectMapper.writeValueAsString(
          CreateChefDto(
            "User wants change password",
            "userwantschangepassword",
            "@Test123",
            "userwantschangepassword@email.com"
          )
        )
      )
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
      .extract()
      .jsonPath()
      .getString("data.token")
  }

  @And("Wants to change his password with one that is less than 8 characters")
  fun `Wants to change his password with one that is less than 8 characters`() {
    newPassword = "123"
  }

  @When("He tries to change his password")
  fun `He tries to change his password`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $token")
      .body(objectMapper.writeValueAsString(ChangePasswordDto(newPassword)))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changePasswords)
      .then()
  }

  @Then("Returns unauthorized because he is not logged-in")
  fun `Returns unauthorized because he is not logged-in`() {
    val errors =
      performRequest
        .log()
        .all()
        .contentType(ContentType.JSON)
        .statusCode(HttpStatus.UNAUTHORIZED.value())
        .extract()
        .response()
        .jsonPath()
        .getString("errors")

    Assertions.assertThat(errors).isNotEqualTo("[]")
  }

  @Then("Returns a bad request for invalid password")
  fun `Returns a bad request for invalid password`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.BAD_REQUEST.value())
  }
}