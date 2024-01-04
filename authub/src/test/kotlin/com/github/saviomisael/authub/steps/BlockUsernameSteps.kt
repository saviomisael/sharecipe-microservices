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
  private var oldUserToken = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A chef is logged-in as spacca")
  fun `A chef is logged-in as spacca`() {
    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "spacca", "@Test123", "oldemail${UUID.randomUUID()}@email.com"))
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

  @Given("A chef is logged-in as spacca2")
  fun `A chef is logged-in`() {
    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "spacca2", "@Test123", "oldemail${UUID.randomUUID()}@email.com"))
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

  @And("Changes his username to pava")
  fun `Changes his username to pava`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("pava"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @And("Changes his username to pava2")
  fun `Changes his username`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("pava2"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @When("Another person tries to create an account with spacca username")
  fun `Another person tries to create an account with spacca username`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("another person", "spacca", "@Test123", "another-${UUID.randomUUID()}@email.com"))
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
  }

  @When("He tries to get a refreshToken with the old token")
  fun `He tries to get a refreshToken with the old token`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .header("Authorization", "Bearer $oldUserToken")
      .`when`()
      .post(ApiRoutes.ChefRoutes.refreshToken)
      .then()
  }

  @Then("Returns a 422 status code")
  fun `Returns a 422 status code`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
  }

  @Then("Returns a 401 status code because spacca2 does not exist")
  fun `Returns a 401 status code because spacca2 does not exist`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNAUTHORIZED.value())
  }
}