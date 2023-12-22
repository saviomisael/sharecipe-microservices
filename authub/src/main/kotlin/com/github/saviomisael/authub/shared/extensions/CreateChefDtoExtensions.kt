package com.github.saviomisael.authub.shared.extensions

import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.shared.builder.ChefBuilder

fun CreateChefDto.toChef(): Chef =
    ChefBuilder.createBuilder().withUsername(this.username).withEmail(this.email).withPassword(this.password)
        .withFullName(this.fullName).build()