package com.github.saviomisael.authub.adapter.infrastructure.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService @Autowired constructor(private val authenticationManager: AuthenticationManager) {
  fun credentialsAreCorrect(username: String, password: String): Boolean {
    try {
      val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

      return authentication.isAuthenticated
    } catch(ex: BadCredentialsException) {
      return false
    }
  }
}