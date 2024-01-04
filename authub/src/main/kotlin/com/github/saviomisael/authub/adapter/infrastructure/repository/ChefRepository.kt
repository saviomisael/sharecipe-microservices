package com.github.saviomisael.authub.adapter.infrastructure.repository

import com.github.saviomisael.authub.adapter.infrastructure.persistence.BlockedUsernameRepository
import com.github.saviomisael.authub.adapter.infrastructure.persistence.ChefDtoRepository
import com.github.saviomisael.authub.adapter.infrastructure.security.BlockedUsername
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.shared.extensions.toChef
import com.github.saviomisael.authub.shared.extensions.toChefDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ChefRepository @Autowired constructor(
  private val chefDtoRepository: ChefDtoRepository,
  private val blockedUsernameRepository: BlockedUsernameRepository
) : IChefRepository {
  override fun saveChefCredentials(chef: Chef): Chef {
    return chefDtoRepository.save(chef.toChefDto()).toChef()
  }

  override fun chefUsernameAlreadyExists(username: String): Boolean {
    return chefDtoRepository.findByUsername(username) != null
  }

  override fun chefEmailAlreadyUsed(email: String) = chefDtoRepository.findByEmail(email) != null
  override fun getByUsername(username: String): Chef? = chefDtoRepository.findByUsername(username)?.toChef()
  override fun changePassword(username: String, newPasswordEncrypted: String) {
    val chefFromDb = chefDtoRepository.getByUsername(username)

    chefFromDb.password = newPasswordEncrypted

    chefDtoRepository.save(chefFromDb)
  }

  override fun changeUsername(username: String, newUsername: String): Chef {
    val chef = chefDtoRepository.getByUsername(username)

    chef.username = newUsername

    return chefDtoRepository.save(chef).toChef()
  }

  override fun getBlockedUsername(username: String) = blockedUsernameRepository.findByUsername(username)

  override fun blockUsername(username: String) {
    blockedUsernameRepository.save(BlockedUsername.build(username))
  }

  override fun unblockUsername(username: String) {
    blockedUsernameRepository.deleteByUsername(username)
  }
}