package com.github.saviomisael.authub.unit.shared.extensions

import com.github.saviomisael.authub.shared.extensions.plusMinutes
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.Instant

class InstantExtensionsTests {
  @Test
  fun `should return a date with x minutes in the future`() {
    val date = Instant.now()
    val after30Minutes = date.plusMinutes(30)
    val duration = Duration.between(date, after30Minutes)

    Assertions.assertThat(after30Minutes).isAfter(date)
    Assertions.assertThat(duration).isEqualTo(Duration.ofMinutes(30))
  }
}