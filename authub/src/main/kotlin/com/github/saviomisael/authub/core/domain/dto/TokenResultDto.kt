package com.github.saviomisael.authub.core.domain.dto

import java.util.*

data class TokenResultDto(val token: String, val username: String, val fullName: String, val expiresAt: Date)