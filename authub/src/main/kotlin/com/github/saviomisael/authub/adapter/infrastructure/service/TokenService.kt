package com.github.saviomisael.authub.adapter.infrastructure.service

import com.github.saviomisael.authub.adapter.infrastructure.dto.TokenDto
import com.github.saviomisael.authub.adapter.infrastructure.dto.TokenPayloadDto
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class TokenService(@Value("\${jwt.secret}") private val secret: String) {
  private val expirationTime: Long = 1000 * 60 * 30
  private val algorithm = SignatureAlgorithm.HS256

  fun decodeToken(token: String): TokenPayloadDto? {
    return try {
      TokenPayloadDto(Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).body.subject)
    } catch (ex: JwtException) {
      null
    }
  }

  fun generateToken(userName: String): TokenDto {
    val claims: MutableMap<String, Any> = HashMap()

    val now = Instant.now()
    val expiresAt = now.plusMillis(expirationTime)

    return TokenDto(
      Jwts.builder()
        .setClaims(claims)
        .setSubject(userName)
        .setIssuedAt(Date.from(now))
        .setNotBefore(Date.from(now.minusMillis(1000)))
        .setExpiration(Date.from(expiresAt))
        .signWith(getSignKey(), algorithm)
        .compact(),
      Date.from(expiresAt)
    )
  }

  private fun getSignKey() = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
}