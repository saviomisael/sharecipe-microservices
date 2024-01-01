package com.github.saviomisael.authub.adapter.infrastructure.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordEncrypterService(@Autowired private val passwordEncoder: PasswordEncoder) {
  fun encryptPassword(password: String): String = passwordEncoder.encode(password)
}