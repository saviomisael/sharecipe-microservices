package com.github.saviomisael.authub.adapter.presentation.dto

import com.github.saviomisael.authub.adapter.presentation.validators.ValidPassword
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateChefDto(
    @field:Size(message = "fullName must have between 2 and 255 characters.", min = 2, max = 255) val fullName: String,
    @field:Size(message = "username must have between 2 and 255 characters.", min = 2, max = 255) val username: String,
    @field:ValidPassword val password: String,
    @field:Email(message = "provide a valid email.") @field:NotEmpty(message = "provide a valid email.") val email: String
)