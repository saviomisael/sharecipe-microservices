package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.AuthenticationService
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.ISignInUseCase
import com.github.saviomisael.authub.shared.exceptions.CredentialsInvalidException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SignInUseCase @Autowired constructor(
  private val authenticationService: AuthenticationService,
  private val chefRepository: IChefRepository,
  private val tokenService: TokenService
) : ISignInUseCase {
  override fun handle(username: String, password: String): TokenResultDto {
    val usernameExists = chefRepository.chefUsernameAlreadyExists(username)

    if (!usernameExists) throw CredentialsInvalidException()

    if (!authenticationService.credentialsAreCorrect(username, password)) throw CredentialsInvalidException()

    val chef = chefRepository.getByUsername(username) ?: throw CredentialsInvalidException()

    val tokenInfo = tokenService.generateToken(username)

    return TokenResultDto(tokenInfo.token, chef.username, chef.fullName, tokenInfo.expiresAt)
  }
}