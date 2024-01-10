package com.github.saviomisael.authub.shared.exceptions

class UsernameAlreadyExistsException(username: String) : RuntimeException("Username $username already exists")