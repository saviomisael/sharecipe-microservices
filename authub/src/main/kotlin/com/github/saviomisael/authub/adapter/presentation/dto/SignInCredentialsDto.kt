package com.github.saviomisael.authub.adapter.presentation.dto

import com.github.saviomisael.authub.adapter.presentation.validators.ValidPassword
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class SignInCredentialsDto(
  @field:Schema(
    description = "Your username that you will use to login.",
    example = "torvalds",
    minLength = 2,
    maxLength = 255
  )
  @field:Size(message = "username must have between 2 and 255 characters", min = 2, max = 255) val username: String,
  @field:Schema(description = "Your strong password.", minLength = 8, maxLength = 255, example = "Test123@")
  @field:ValidPassword val password: String
)