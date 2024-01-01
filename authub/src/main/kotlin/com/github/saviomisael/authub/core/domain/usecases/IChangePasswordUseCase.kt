package com.github.saviomisael.authub.core.domain.usecases

import com.github.saviomisael.authub.core.domain.dto.TokenResultDto

interface IChangePasswordUseCase {
  fun handle(username: String, newPassword: String): TokenResultDto
}