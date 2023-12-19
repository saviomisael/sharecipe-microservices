package com.github.saviomisael.authub.adapter.infrastructure.jpa

import com.github.saviomisael.authub.adapter.infrastructure.dto.ChefDto
import org.springframework.data.jpa.repository.JpaRepository

interface ChefDtoRepository : JpaRepository<ChefDto, String> {
}