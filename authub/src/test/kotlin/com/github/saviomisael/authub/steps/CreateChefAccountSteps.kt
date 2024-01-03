package com.github.saviomisael.authub.steps

import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matchers
import org.springframework.http.HttpStatus

class CreateChefAccountSteps {
  private var fullName = ""
  private var username = ""
  private var password = ""
  private var email = ""
  private lateinit var performRequest: ValidatableResponse

  @Given("A person that provides your full name in a invalid way")
  fun `A person that provides your full name in a invalid way`() {
    fullName = ""
  }

  @Given("A person that provides an invalid username")
  fun `A person that provides an invalid username`() {
    username = ""
  }

  @Given("A person that provides a password less than 8 characters to create an account")
  fun `A person that provides a password less than 8 characters to create an account`() {
    password = "123"
  }

  @Given("A person that provides a password almost valid but does not have a uppercase letter")
  fun `A person that provides a password almost valid but does not have a uppercase letter`() {
    password = "test123@"
  }

  @Given("A person that provides a password almost valid but does not have a lowercase letter")
  fun `A person that provides a password almost valid but does not have a lowercase letter`() {
    password = "TEST123@"
  }

  @Given("A person provides a password with more than 255 characters")
  fun `A person provides a password with more than 255 characters`() {
    password = "test123".repeat("test123".length * 37)
  }

  @Given("A person provides a password without numbers")
  fun `A person provides a password without numbers`() {
    password = "test_test@"
  }

  @Given("A person provides a password without symbols")
  fun `A person provides a password without symbols`() {
    password = "test123test"
  }

  @Given("A person provides all the information but the email is invalid")
  fun `A person provides all the information but the email is invalid`() {
    fullName = "Test"
    username = "test"
    password = "Test123@"
    email = ""
  }

  @Given("A person provides an username that already is in use")
  fun `A person provides an username that already is in use`() {
    username = "salamander"
    fullName = "Salamander"
    password = "salamanDer@123"
    email = "salamander@mail.com"

    RestAssured
      .given()
      .contentType(ContentType.JSON)
      .body(
        CreateChefDto(
          username, fullName, password, email
        )
      )
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
  }

  @Given("A person provides an email that already is in use")
  fun `A person provides an email that already is in use`() {
    username = "salamander1"
    fullName = "Salamander"
    password = "salamanDer@123"
    email = "salamander@mail.com"
  }

  @Given("A person provides all information to create account with all fields valid")
  fun `A person provides all information to create account with all fields valid`() {
    username = "thesalamander"
    fullName = "The Salamander"
    password = "salamanDer@123"
    email = "thesalamander@mail.com"
  }

  @When("This person tries to create an account")
  fun `This person tries to create an account`() {
    performRequest = RestAssured
      .given()
      .log()
      .all()
      .contentType(ContentType.JSON)
      .body(
        CreateChefDto(
          fullName, username, password, email
        )
      )
      .`when`()
      .post(ApiRoutes.ChefRoutes.createChefAccount)
      .then()
  }

  @Then("The person should get a bad request response")
  fun `The person should get a bad request response`() {
    performRequest
      .log()
      .all()
      .statusCode(HttpStatus.BAD_REQUEST.value())
      .contentType(ContentType.JSON)
  }

  @Then("The person should get an unprocessable entity response")
  fun `The person should get an unprocessable entity response`() {
    performRequest
      .log()
      .all()
      .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
      .contentType(ContentType.JSON)
  }

  @Then("The person should get a created response")
  fun `The person should get a created response`() {
    performRequest
      .log()
      .all()
      .statusCode(HttpStatus.CREATED.value())
      .contentType(ContentType.JSON)
      .body("data.username", Matchers.equalTo("thesalamander"))
      .body("data.fullName", Matchers.equalTo("The Salamander"))
  }
}