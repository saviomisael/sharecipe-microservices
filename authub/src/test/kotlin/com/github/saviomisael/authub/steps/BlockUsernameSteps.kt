package com.github.saviomisael.authub.steps

import com.github.saviomisael.authub.adapter.presentation.dto.ChangePasswordDto
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
  private var secondChefToken = ""
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

  @Given("A chef is logged-in as spacca3")
  fun `A chef is logged-in as spacca3`() {
    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "spacca3", "@Test123", "oldemail3${UUID.randomUUID()}@email.com"))
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

  @Given("A chef is logged-in as spacca4")
  fun `A chef is logged-in as spacca4`() {
    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "spacca4", "@Test123", "oldemail4${UUID.randomUUID()}@email.com"))
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

  @Given("A chef is logged-in as spacca5")
  fun `A chef is logged-in as spacca5`() {
    oldUserToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "spacca5", "@Test123", "oldemail5${UUID.randomUUID()}@email.com"))
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

  @And("Changes his username to pava3")
  fun `Changes his username to pava3`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("pava3"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @And("Changes his username to pava4")
  fun `Changes his username to pava4`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("pava4"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @And("Changes his username to pava5")
  fun `Changes his username to pava5`() {
    RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("pava5"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @And("A chef is logged-in as bida")
  fun `A chef is logged-in as bida`() {
    secondChefToken = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(CreateChefDto("full name for old user", "bida", "@Test123", "bida${UUID.randomUUID()}@email.com"))
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

  @When("He tries to change his password for his old username")
  fun `He tries to change his password for his old username`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangePasswordDto("@TEStTes123"))
      .`when`()
      .post(ApiRoutes.ChefRoutes.changePassword)
      .then()
  }

  @When("He tries to change his username for his old username")
  fun `He tries to change his username for his old username`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .header("Authorization", "Bearer $oldUserToken")
      .body(ChangeUsernameDto("potatohat"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
  }

  @When("bida tries to change his username to spacca5")
  fun `bida tries to change his username to spacca5`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .header("Authorization", "Bearer $secondChefToken")
      .body(ChangeUsernameDto("spacca5"))
      .`when`()
      .patch(ApiRoutes.ChefRoutes.changeUsername)
      .then()
      .log()
      .all()
  }

  @Then("Returns a 422 status code")
  fun `Returns a 422 status code`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
  }

  @Then("Returns a 401 status code because old username does not exist")
  fun `Returns a 401 status code because old username does not exist`() {
    performRequest
      .log()
      .all()
      .contentType(ContentType.JSON)
      .statusCode(HttpStatus.UNAUTHORIZED.value())
  }
}