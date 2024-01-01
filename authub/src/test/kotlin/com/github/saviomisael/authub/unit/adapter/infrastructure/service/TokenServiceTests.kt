package com.github.saviomisael.authub.unit.adapter.infrastructure.service

import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TokenServiceTests {
  private val tokenService =
    TokenService("d5efbc8f138194c595b26ea6b84a0afde49dd51991ff4181ea462da8d0fbb9704af535f6f3d01b691c6b1b0c8e610b2bebe6250aeaf84679764bda21719f5912")

  @Test
  fun `expiresAt should be in the future`() {
    val expiresAt = tokenService.generateToken("username").expiresAt

    Assertions.assertThat(expiresAt).isInTheFuture()
  }
}