package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.BlockedUsernameService
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.IChangeUsernameUseCase
import com.github.saviomisael.authub.shared.exceptions.UsernameAlreadyExistsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangeUsernameUseCase @Autowired constructor(
  private val chefRepository: IChefRepository,
  private val tokenService: TokenService,
  private val blockedUsernameService: BlockedUsernameService
) : IChangeUsernameUseCase {
  override fun handle(username: String, newUsername: String): TokenResultDto {
    // TODO - Check if newusername is available
    val newUsernameAlreadyExists = chefRepository.chefUsernameAlreadyExists(newUsername)

    if (newUsernameAlreadyExists) throw UsernameAlreadyExistsException(newUsername)

    val chef = chefRepository.changeUsername(username, newUsername)
    blockedUsernameService.blockUsername(username)
    // TODO - Update recipes with new username
    val tokenInfo = tokenService.generateToken(chef.username)

    return TokenResultDto(tokenInfo.token, chef.username, chef.fullName, tokenInfo.expiresAt)
  }
}