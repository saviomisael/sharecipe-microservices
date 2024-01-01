package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.ChangePasswordDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
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
  private lateinit var performRequest: ValidatableResponse

  @Given("A user without a token")
  fun `A user without a token`() {
    token = ""
  }

  @When("He tries to change his password")
  fun `He tries to change his password`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .body(objectMapper.writeValueAsString(ChangePasswordDto("@Test123")))
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
}