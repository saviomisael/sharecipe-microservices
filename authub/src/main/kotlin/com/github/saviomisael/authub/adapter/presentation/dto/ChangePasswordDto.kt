package com.github.saviomisael.authub.adapter.presentation.dto

import com.github.saviomisael.authub.adapter.presentation.validators.ValidPassword
import io.swagger.v3.oas.annotations.media.Schema

data class ChangePasswordDto(
  @field:Schema(description = "Your new strong password.", minLength = 8, maxLength = 255, example = "Test123@")
  @field:ValidPassword val password: String
)