package com.github.saviomisael.authub.shared.exceptions

class ChefNotFoundException(private val username: String) : RuntimeException("Chef $username not found.") {
}