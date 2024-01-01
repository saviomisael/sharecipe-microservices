package com.github.saviomisael.authub.unit.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.application.usecases.GenerateRefreshTokenUseCase
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IGenerateRefreshTokenUseCase
import com.github.saviomisael.authub.shared.exceptions.TokenInvalidException
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GenerateRefreshTokenUseCaseTests {
  private val tokenService = mockk<TokenService>()
  private val chefRepositoryMock = mockk<IChefRepository>()
  private lateinit var useCase: IGenerateRefreshTokenUseCase

  @Test
  fun `should throws TokenInvalidException when chef username does not exist`() {
    every { chefRepositoryMock.getByUsername(any()) } returns null

    useCase = GenerateRefreshTokenUseCase(tokenService, chefRepositoryMock)

    Assertions.assertThatExceptionOfType(TokenInvalidException::class.java)
      .isThrownBy {
        useCase.handle("token")
      }
  }
}