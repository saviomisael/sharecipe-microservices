package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IChangeUsernameUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangeUsernameUseCase @Autowired constructor(
  private val chefRepository: IChefRepository,
  private val tokenService: TokenService
) : IChangeUsernameUseCase {
  override fun handle(username: String, newUsername: String): TokenResultDto {
    val chef = chefRepository.changeUsername(username, newUsername)
    chefRepository.blockUsername(username)
    // TODO - Update recipes with new username
    val tokenInfo = tokenService.generateToken(chef.username)

    return TokenResultDto(tokenInfo.token, chef.username, chef.fullName, tokenInfo.expiresAt)
  }
}