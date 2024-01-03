package com.github.saviomisael.authub.adapter.presentation.v1

object ApiRoutes {
  private const val ROOT = "api"
  private const val VERSION = "v1"
  private const val BASE = "/$ROOT/$VERSION"

  object ChefRoutes {
    const val createChefAccount = "$BASE/chefs/"
    const val signIn = "$BASE/chefs/tokens/"
    const val refreshToken = "$BASE/chefs/tokens/refresh-tokens/"
    const val changePassword = "$BASE/chefs/passwords/"
    const val changeUsername = "$BASE/chefs/usernames/"
  }
}