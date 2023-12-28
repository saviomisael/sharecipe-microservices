package com.github.saviomisael.authub.core.domain.usecases

import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.entity.Chef

interface ISaveChefCredentialsUseCase {
  fun handle(chef: Chef): TokenResultDto
}