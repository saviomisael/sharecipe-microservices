package com.github.saviomisael.authub.shared.extensions

import com.github.saviomisael.authub.adapter.infrastructure.dto.ChefDto
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.shared.builder.ChefDtoBuilder

fun Chef.toChefDto(): ChefDto {
    return ChefDtoBuilder.createBuilder()
        .withEmail(this.email)
        .withPassword(this.password)
        .withUsername(this.username)
        .withFullName(this.fullName)
        .build()
}