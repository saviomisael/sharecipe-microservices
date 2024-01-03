package com.github.saviomisael.authub.core.domain.usecases

import com.github.saviomisael.authub.core.domain.dto.TokenResultDto

interface IChangeUsernameUseCase {
  fun handle(username: String, newUsername: String): TokenResultDto
}