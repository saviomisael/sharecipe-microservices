package com.github.saviomisael.authub.core.domain.usecases

interface IChangePasswordUseCase {
  fun handle(username: String, newPassword: String)
}