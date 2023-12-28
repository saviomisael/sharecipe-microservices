package com.github.saviomisael.authub.adapter.presentation.dto

import com.github.saviomisael.authub.adapter.presentation.validators.ValidPassword
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(description = "Data to create a chef account.")
data class CreateChefDto(
  @field:Schema(description = "Your full name.", example = "Linus Torvalds", minLength = 2, maxLength = 255)
  @field:Size(message = "fullName must have between 2 and 255 characters.", min = 2, max = 255) val fullName: String,
  @field:Schema(
    description = "Your username that you will use to login.",
    example = "torvalds",
    minLength = 2,
    maxLength = 255
  )
  @field:Size(message = "username must have between 2 and 255 characters.", min = 2, max = 255) val username: String,
  @field:Schema(description = "Your strong password.", minLength = 8, maxLength = 255, example = "Test123@")
  @field:ValidPassword val password: String,
  @field:Schema(description = "Your email.", minLength = 1, maxLength = 255, example = "linus@mail.com")
  @field:Email(message = "provide a valid email.") @field:NotEmpty(message = "provide a valid email.") @Size(
    message = "email length must be less than 255 characters.",
    max = 255
  ) val email: String
)