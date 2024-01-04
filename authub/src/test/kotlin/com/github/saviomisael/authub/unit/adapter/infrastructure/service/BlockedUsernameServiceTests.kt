package com.github.saviomisael.authub.unit.adapter.infrastructure.service

import com.github.saviomisael.authub.adapter.infrastructure.security.BlockedUsername
import com.github.saviomisael.authub.adapter.infrastructure.service.BlockedUsernameService
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.shared.extensions.plusMinutes
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class BlockedUsernameServiceTests {
  private val chefRepository = mockk<IChefRepository>()
  private val blockedUsernameService = BlockedUsernameService(chefRepository)

  @Test
  fun `should return true when username is not blocked`() {
    every {
      chefRepository.getBlockedUsername(any())
    } returns null

    val isAvailableUsername = blockedUsernameService.isAvailableUsername("username")

    Assertions.assertThat(isAvailableUsername).isTrue()
  }

  @Test
  fun `should return false when username is blocked`() {
    val blockedUsername = BlockedUsername.build("username")

    every {
      chefRepository.getBlockedUsername(any())
    } returns blockedUsername

    val isAvailableUsername = blockedUsernameService.isAvailableUsername("username")

    Assertions.assertThat(isAvailableUsername).isFalse()
  }

  @Test
  fun `should return true when block expires`() {
    val fixedInstant = Instant.parse("2018-08-22T10:00:00Z")
    val fixedClock = Clock.fixed(fixedInstant, ZoneOffset.UTC)

    val blockedUsername = BlockedUsername.build("username")
    blockedUsername.expiresBlockAt = Instant.now(fixedClock).plusMinutes(35)

    every {
      chefRepository.getBlockedUsername(any())
    } returns blockedUsername

    every {
      chefRepository.unblockUsername(any())
    } just Runs

    val isAvailableUsername = blockedUsernameService.isAvailableUsername("username")

    Assertions.assertThat(isAvailableUsername).isTrue()
  }
}