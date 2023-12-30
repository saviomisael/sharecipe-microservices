package com.github.saviomisael.authub.steps

import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matchers
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
  properties = ["spring.profiles.active=qa"]
)
class GenerateRefreshTokenSteps {
  private var token = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef or service provide a invalid token")
  fun `A chef or service provide a invalid token`() {
    token =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
  }

  @When("Tries to get a new token")
  fun `Tries to get a new token`() {
    performRequest =
      RestAssured
        .given()
        .log()
        .all()
        .contentType(ContentType.JSON)
        .header("Authorization", "Bearer $token")
        .`when`()
        .post(ApiRoutes.ChefRoutes.refreshToken)
        .then()
  }

  @Then("Returns unauthorized because the token is invalid")
  fun `Returns unauthorized because the token is invalid`() {
    performRequest
      .log()
      .all()
      .statusCode(HttpStatus.UNAUTHORIZED.value())
      .contentType(ContentType.JSON)
      .body("errors[0]", Matchers.equalTo("Token provided is invalid."))
  }
}