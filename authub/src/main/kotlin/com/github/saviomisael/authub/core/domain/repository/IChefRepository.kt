package com.github.saviomisael.authub.core.domain.repository

import com.github.saviomisael.authub.core.domain.entity.Chef

interface IChefRepository {
  fun saveChefCredentials(chef: Chef): Chef
  fun chefUsernameAlreadyExists(username: String): Boolean
  fun chefEmailAlreadyUsed(email: String): Boolean
  fun getByUsername(username: String): Chef?
  fun changePassword(username: String, newPasswordEncrypted: String)
}