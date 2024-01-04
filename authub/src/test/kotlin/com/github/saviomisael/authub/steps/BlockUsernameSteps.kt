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

class BlockUsernameSteps {
  private var oldUsername = ""
  private var oldUserToken = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef is logged-in")
  fun `A chef is logged-in`() {
    oldUsername = "old-${UUID.randomUUID()}"

    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", oldUsername, "@Test123", "oldemail${UUID.randomUUID()}@email.com"))
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

  @And("Changes his username")
  fun `Changes his username`() {
    RestAssured
      .given()
      .log()
      .all()
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("new-${UUID.randomUUID()}"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @When("Another person tries to create an account with the old username")
  fun `Another person tries to create an account with the old username`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("another person", oldUsername, "@Test123", "another-${UUID.randomUUID()}@email.com"))
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
  }

  @Then("Returns a 422 response")
  fun `Returns a 422 response`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
  }
}