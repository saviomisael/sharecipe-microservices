package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.PasswordEncrypterService
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IChangePasswordUseCase
import com.github.saviomisael.authub.shared.exceptions.ChefNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangePasswordUseCase @Autowired constructor(
  private val tokenService: TokenService,
  private val chefRepository: IChefRepository,
  private val passwordEncrypterService: PasswordEncrypterService
) : IChangePasswordUseCase {
  override fun handle(username: String, newPassword: String): TokenResultDto {
    val chef = chefRepository.changePassword(username, passwordEncrypterService.encryptPassword(newPassword)) ?: throw ChefNotFoundException(username)

    val tokenInfo = tokenService.generateToken(chef.username)

    return TokenResultDto(tokenInfo.token, chef.username, chef.fullName, tokenInfo.expiresAt)
  }
}