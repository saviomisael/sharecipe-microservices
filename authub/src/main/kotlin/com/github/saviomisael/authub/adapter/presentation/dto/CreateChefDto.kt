package com.github.saviomisael.authub.adapter.presentation.dto

import com.github.saviomisael.authub.adapter.presentation.validators.ValidPassword
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class CreateChefDto(
    @Size(message = "fullName must have between 2 and 255 characters.", min = 2, max = 255) val fullName: String,
    @Size(message = "username must have between 2 and 255 characters.", min = 2, max = 255) val username: String,
    @ValidPassword val password: String,
    @Email(message = "provide a valid email.") val email: String
)