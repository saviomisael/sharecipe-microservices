package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.PasswordEncrypterService
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IChangePasswordUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangePasswordUseCase @Autowired constructor(
  private val chefRepository: IChefRepository,
  private val passwordEncrypterService: PasswordEncrypterService
) : IChangePasswordUseCase {
  override fun handle(username: String, newPassword: String) {
    chefRepository.changePassword(username, passwordEncrypterService.encryptPassword(newPassword))
  }
}