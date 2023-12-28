package com.github.saviomisael.authub.shared.builder

import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.shared.exceptions.EmptyStringException

class ChefBuilder {
  private var fullName = ""
  private var username = ""
  private var password = ""
  private var email = ""

  companion object {
    fun createBuilder(): ChefBuilder {
      return ChefBuilder()
    }
  }

  fun withFullName(fullNameParam: String): ChefBuilder {
    if (fullNameParam.isEmpty()) throw EmptyStringException("fullName")

    this.fullName = fullNameParam
    return this
  }

  fun withUsername(usernameParam: String): ChefBuilder {
    if (usernameParam.isEmpty()) throw EmptyStringException("username")

    this.username = usernameParam
    return this
  }

  fun withPassword(passwordParam: String): ChefBuilder {
    if (passwordParam.isEmpty()) throw EmptyStringException("password")

    this.password = passwordParam
    return this
  }

  fun withEmail(emailParam: String): ChefBuilder {
    if (emailParam.isEmpty()) throw EmptyStringException("email")

    this.email = emailParam
    return this
  }

  fun build(): Chef {
    return Chef(fullName, username, password, email)
  }
}