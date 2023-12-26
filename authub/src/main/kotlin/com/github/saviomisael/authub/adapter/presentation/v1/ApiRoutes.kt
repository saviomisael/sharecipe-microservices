package com.github.saviomisael.authub.adapter.presentation.v1

object ApiRoutes {
    private const val ROOT = "api"
    private const val VERSION = "v1"
    private const val BASE = "/$ROOT/$VERSION"

    object ChefRoutes {
        const val createChefAccount = "$BASE/chefs"
    }
}