package com.github.saviomisael.authub.core.application.usecases

import com.github.saviomisael.authub.adapter.infrastructure.service.PasswordEncrypterService
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.core.domain.usecases.ISaveChefCredentialsUseCase
import com.github.saviomisael.authub.shared.builder.ChefBuilder
import com.github.saviomisael.authub.shared.exceptions.EmailAlreadyUsedException
import com.github.saviomisael.authub.shared.exceptions.UsernameAlreadyExistsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveChefCredentialsUseCase(
  @Autowired private val chefRepository: IChefRepository,
  @Autowired private val passwordEncrypterService: PasswordEncrypterService,
  @Autowired private val tokenService: TokenService
) : ISaveChefCredentialsUseCase {
  override fun handle(chef: Chef): TokenResultDto {
    val usernameAlreadyExists = chefRepository.chefUsernameAlreadyExists(chef.username)

    if (usernameAlreadyExists) throw UsernameAlreadyExistsException(chef.username)

    val emailAlreadyUsed = chefRepository.chefEmailAlreadyUsed(chef.email)

    if (emailAlreadyUsed) throw EmailAlreadyUsedException(chef.email)

    var chefWithPasswordHashed =
      ChefBuilder.createBuilder().withFullName(chef.fullName).withUsername(chef.username).withEmail(chef.email)
        .withPassword(passwordEncrypterService.encryptPassword(chef.password)).build()

    chefWithPasswordHashed = chefRepository.saveChefCredentials(chefWithPasswordHashed)

    return TokenResultDto(
      tokenService.generateToken(chefWithPasswordHashed.username),
      chefWithPasswordHashed.username,
      chefWithPasswordHashed.fullName
    )
  }
}