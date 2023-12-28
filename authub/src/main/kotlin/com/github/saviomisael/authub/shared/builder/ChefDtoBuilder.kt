package com.github.saviomisael.authub.shared.builder

import com.github.saviomisael.authub.adapter.infrastructure.dto.ChefDto
import com.github.saviomisael.authub.shared.exceptions.EmptyStringException

class ChefDtoBuilder {
  private var fullName = ""
  private var username = ""
  private var password = ""
  private var email = ""

  companion object {
    fun createBuilder(): ChefDtoBuilder {
      return ChefDtoBuilder()
    }
  }

  fun withFullName(fullNameParam: String): ChefDtoBuilder {
    if (fullNameParam.isEmpty()) throw EmptyStringException("fullName")

    this.fullName = fullNameParam
    return this
  }

  fun withUsername(usernameParam: String): ChefDtoBuilder {
    if (usernameParam.isEmpty()) throw EmptyStringException("username")

    this.username = usernameParam
    return this
  }

  fun withPassword(passwordParam: String): ChefDtoBuilder {
    if (passwordParam.isEmpty()) throw EmptyStringException("password")

    this.password = passwordParam
    return this
  }

  fun withEmail(emailParam: String): ChefDtoBuilder {
    if (emailParam.isEmpty()) throw EmptyStringException("email")

    this.email = emailParam
    return this
  }

  fun build(): ChefDto {
    val chefDto = ChefDto()
    chefDto.email = email
    chefDto.fullName = fullName
    chefDto.password = password
    chefDto.username = username

    return chefDto
  }
}