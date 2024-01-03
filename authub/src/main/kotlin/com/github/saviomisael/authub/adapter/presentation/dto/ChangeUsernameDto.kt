package com.github.saviomisael.authub.adapter.presentation.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class ChangeUsernameDto(
  @field:Schema(
    description = "Your new username that you will use to login.",
    example = "linus-torvalds",
    minLength = 2,
    maxLength = 255
  )
  @field:Size(
    message = "newUsername must have between 2 and 255 characters.",
    min = 2,
    max = 255
  ) val newUsername: String
)