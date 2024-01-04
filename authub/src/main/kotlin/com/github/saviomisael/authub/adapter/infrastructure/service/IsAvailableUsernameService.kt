package com.github.saviomisael.authub.adapter.infrastructure.service

import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IsAvailableUsernameService @Autowired constructor(private val chefRepository: IChefRepository) {
  fun isAvailableUsername(username: String): Boolean {
    val blockedUsername = chefRepository.getBlockedUsername(username) ?: return true

    val isAvailableUsername = !blockedUsername.isBlocked()

    if (isAvailableUsername) chefRepository.unblockUsername(username)

    return isAvailableUsername
  }
}