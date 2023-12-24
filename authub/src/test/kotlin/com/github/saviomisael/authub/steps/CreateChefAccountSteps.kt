package com.github.saviomisael.authub.steps

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.post

@CucumberContextConfiguration
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = ["spring.profiles.active=test"]
)
@AutoConfigureMockMvc
class CreateChefAccountSteps @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    private var fullName = ""
    private var username = ""
    private var password = ""
    private lateinit var performRequest: ResultActionsDsl

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

    @When("This person tries to create an account")
    fun this_person_tries_to_create_an_account() {
        performRequest = mockMvc.post("/api/v1/chefs") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(CreateChefDto(fullName, username, password, "email@email.com"))
        }
    }

    @Then("The person should get a bad request response")
    fun the_person_should_get_a_bad_request_response() {
        performRequest.andDo { print() }
            .andExpect {
                status {
                    isBadRequest()
                }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                }
            }
    }
}