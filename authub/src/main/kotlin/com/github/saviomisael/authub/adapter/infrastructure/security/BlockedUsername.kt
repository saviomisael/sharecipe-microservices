package com.github.saviomisael.authub.adapter.infrastructure.security

import com.github.saviomisael.authub.shared.extensions.plusMinutes
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity(name = "blockedusernames")
class BlockedUsername {
  @Id
  @Column(unique = true, nullable = false, length = 255)
  lateinit var username: String

  @Column(unique = false, nullable = false)
  lateinit var expiresBlockAt: Instant

  companion object {
    fun build(username: String) {
      val blockedUsername = BlockedUsername()
      blockedUsername.username = username
      blockedUsername.expiresBlockAt = Instant.now().plusMinutes(30)
    }
  }

  fun isBlocked() = Instant.now().isBefore(expiresBlockAt)
}