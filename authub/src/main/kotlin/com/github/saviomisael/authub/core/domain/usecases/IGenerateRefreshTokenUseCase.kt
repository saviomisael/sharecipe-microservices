package com.github.saviomisael.authub.core.domain.usecases

import com.github.saviomisael.authub.core.domain.dto.TokenResultDto

interface IGenerateRefreshTokenUseCase {
  fun handle(token: String): TokenResultDto
}