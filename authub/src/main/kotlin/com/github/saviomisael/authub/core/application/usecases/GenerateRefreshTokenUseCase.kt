package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IGenerateRefreshTokenUseCase
import com.github.saviomisael.authub.shared.exceptions.TokenInvalidException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GenerateRefreshTokenUseCase @Autowired constructor(
  private val tokenService: TokenService,
  private val chefRepository: IChefRepository
) : IGenerateRefreshTokenUseCase {
  override fun handle(token: String): TokenResultDto {
    val decodedToken = tokenService.decodeToken(token) ?: throw TokenInvalidException()

    val chefExists = chefRepository.chefUsernameAlreadyExists(decodedToken.username)

    if (!chefExists) throw TokenInvalidException()

    val chef = chefRepository.getByUsername(decodedToken.username) ?: throw TokenInvalidException()

    return TokenResultDto(tokenService.generateToken(chef.username), chef.username, chef.fullName)
  }
}