package com.github.saviomisael.authub.adapter.infrastructure.service

import com.github.saviomisael.authub.adapter.infrastructure.dto.TokenPayloadDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class TokenService(@Value("\${jwt.secret}") private val secret: String) {
    private val expirationTime: Long = 1000 * 60 * 30
    private val algorithm = SignatureAlgorithm.HS256

    fun decodeToken(token: String) = TokenPayloadDto(Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJwt(token).body.subject)

    fun generateToken(userName: String): String {
        val claims: MutableMap<String, Any> = HashMap()

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusMillis(expirationTime)))
            .signWith(getSignKey(), algorithm)
            .compact()
    }

    private fun getSignKey() = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
}