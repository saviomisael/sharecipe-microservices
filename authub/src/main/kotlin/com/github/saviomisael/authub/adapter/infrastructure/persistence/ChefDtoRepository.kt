package com.github.saviomisael.authub.adapter.infrastructure.persistence

import com.github.saviomisael.authub.adapter.infrastructure.dto.ChefDto
import org.springframework.data.jpa.repository.JpaRepository

interface ChefDtoRepository : JpaRepository<ChefDto, String> {
  fun findByUsername(username: String): ChefDto?
  fun findByEmail(email: String): ChefDto?
  fun getByUsername(username: String): ChefDto
}