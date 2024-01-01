package com.github.saviomisael.authub.unit.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.PasswordEncrypterService
import com.github.saviomisael.authub.core.application.usecases.ChangePasswordUseCase
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.shared.exceptions.ChefNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ChangePasswordUseCaseTests {
  private val chefRepositoryMock = mockk<IChefRepository>()
  private val passwordEncrypterServiceMock = mockk<PasswordEncrypterService>()
  private val useCase = ChangePasswordUseCase(chefRepositoryMock, passwordEncrypterServiceMock)

  @Test
  fun `should throw a ChefNotFoundException when chef does not exist`() {
    every { chefRepositoryMock.changePassword(any(), any()) } returns null

    every { passwordEncrypterServiceMock.encryptPassword(any()) } returns "password"

    Assertions.assertThatExceptionOfType(ChefNotFoundException::class.java)
      .isThrownBy {
        useCase.handle("username", "password")
      }
  }
}