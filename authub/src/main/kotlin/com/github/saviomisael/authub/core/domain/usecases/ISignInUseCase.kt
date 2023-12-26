package com.github.saviomisael.authub.core.domain.usecases

import com.github.saviomisael.authub.core.domain.dto.TokenResultDto

interface ISignInUseCase {
    fun handle(username: String, password: String): TokenResultDto
}