package com.github.saviomisael.authub.adapter.infrastructure.service

import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlockedUsernameService @Autowired constructor(private val chefRepository: IChefRepository) {
  @Transactional
  fun isAvailableUsername(username: String): Boolean {
    val blockedUsername = chefRepository.getBlockedUsername(username) ?: return true

    val isAvailableUsername = !blockedUsername.isBlocked()

    if (isAvailableUsername) chefRepository.unblockUsername(username)

    return isAvailableUsername
  }

  fun blockUsername(username: String) {
    chefRepository.blockUsername(username)
  }
}