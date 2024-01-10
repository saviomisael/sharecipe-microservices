package com.github.saviomisael.authub.shared.exceptions

class EmailAlreadyUsedException(email: String) : RuntimeException("Email $email already used")