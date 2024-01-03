package com.github.saviomisael.authub.shared.extensions

import java.time.Instant

fun Instant.plusMinutes(minutes: Int) = this.plusSeconds(60L * minutes)