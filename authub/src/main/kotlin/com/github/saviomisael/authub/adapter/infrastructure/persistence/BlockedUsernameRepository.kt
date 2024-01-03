package com.github.saviomisael.authub.adapter.infrastructure.persistence

import com.github.saviomisael.authub.adapter.infrastructure.security.BlockedUsername
import org.springframework.data.jpa.repository.JpaRepository

interface BlockedUsernameRepository : JpaRepository<BlockedUsername, String> {
  fun findByUsername(username: String): BlockedUsername?
  fun deleteByUsername(username: String)
}