package com.github.saviomisael.authub.core.domain.repository

import com.github.saviomisael.authub.adapter.infrastructure.security.BlockedUsername
import com.github.saviomisael.authub.core.domain.entity.Chef

interface IChefRepository {
  fun saveChefCredentials(chef: Chef): Chef
  fun chefUsernameAlreadyExists(username: String): Boolean
  fun chefEmailAlreadyUsed(email: String): Boolean
  fun getByUsername(username: String): Chef?
  fun changePassword(username: String, newPasswordEncrypted: String)
  fun changeUsername(username: String, newUsername: String): Chef
  fun getBlockedUsername(username: String): BlockedUsername?
  fun blockUsername(username: String)
  fun unblockUsername(username: String)
}