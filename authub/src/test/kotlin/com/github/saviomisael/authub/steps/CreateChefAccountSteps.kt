package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import io.cucumber.java.Before
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
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ["spring.profiles.active=qa"]
)
class CreateChefAccountSteps {
    private val objectMapper = ObjectMapper()
    private var fullName = ""
    private var username = ""
    private var password = ""
    private var email = ""
    private lateinit var performRequest: ValidatableResponse

    @Before
    fun setup() {
        RestAssured.port = 8888
    }

    @Given("A person that provides your full name in a invalid way")
    fun a_person_that_provides_your_full_name_in_a_invalid_way() {
        fullName = ""
    }

    @Given("A person that provides an invalid username")
    fun a_person_that_provides_an_invalid_username() {
        username = ""
    }

    @Given("A person that provides a password less than 8 characters to create an account")
    fun a_person_that_provides_a_password_less_than_8_characters_to_create_an_account() {
        password = "123"
    }

    @Given("A person that provides a password almost valid but does not have a uppercase letter")
    fun a_person_that_provides_a_password_almost_valid_but_does_not_have_a_uppercase_letter() {
        password = "test123@"
    }

    @Given("A person that provides a password almost valid but does not have a lowercase letter")
    fun a_person_that_provides_a_password_almost_valid_but_does_not_have_a_lowercase_letter() {
        password = "TEST123@"
    }

    @Given("A person provides a password with more than 255 characters")
    fun a_person_provides_a_password_with_more_than_255_characters() {
        password = "test123".repeat("test123".length * 37)
    }

    @Given("A person provides a password without numbers")
    fun a_person_provides_a_password_without_numbers() {
        password = "test_test@"
    }

    @Given("A person provides a password without symbols")
    fun a_person_provides_a_password_without_symbols() {
        password = "test123test"
    }

    @Given("A person provides all the information but the email is invalid")
    fun a_person_provides_all_the_information_but_the_email_is_invalid() {
        fullName = "Test"
        username = "test"
        password = "Test123@"
        email = ""
    }

    @Given("A person provides an username that already is in use")
    fun a_person_provides_an_username_that_already_is_in_use() {
        username = "salamander"
        fullName = "Salamander"
        password = "salamanDer@123"
        email = "salamander@mail.com"

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(
                objectMapper.writeValueAsString(
                    CreateChefDto(
                        username, fullName, password, email
                    )
                )
            )
            .`when`()
            .post(ApiRoutes.ChefRoutes.createChefAccount)
            .then()
    }

    @Given("A person provides an email that already is in use")
    fun a_person_provides_an_email_that_already_is_in_use() {
        username = "salamander1"
        fullName = "Salamander"
        password = "salamanDer@123"
        email = "salamander@mail.com"
    }

    @Given("A person provides all information to create account with all fields valid")
    fun a_person_provides_all_information_to_create_account_with_all_fields_valid() {
        username = "thesalamander"
        fullName = "The Salamander"
        password = "salamanDer@123"
        email = "thesalamander@mail.com"
    }

    @When("This person tries to create an account")
    fun this_person_tries_to_create_an_account() {
        performRequest = RestAssured
            .given()
            .log()
            .all()
            .contentType(ContentType.JSON)
            .body(
                objectMapper.writeValueAsString(
                    CreateChefDto(
                        fullName, username, password, email
                    )
                )
            )
            .`when`()
            .post(ApiRoutes.ChefRoutes.createChefAccount)
            .then()
    }

    @Then("The person should get a bad request response")
    fun the_person_should_get_a_bad_request_response() {
        performRequest
            .log()
            .all()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .contentType(ContentType.JSON)
    }

    @Then("The person should get an unprocessable entity response")
    fun the_person_should_get_an_unprocessable_entity_response() {
        performRequest
            .log()
            .all()
            .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .contentType(ContentType.JSON)
    }

    @Then("The person should get a created response")
    fun the_person_should_get_a_created_response() {
        performRequest
            .log()
            .all()
            .statusCode(HttpStatus.CREATED.value())
            .contentType(ContentType.JSON)
            .body("data.username", Matchers.equalTo("thesalamander"))
            .body("data.fullName", Matchers.equalTo("The Salamander"))
    }
}