package com.github.saviomisael.authub.shared.exceptions

class EmptyStringException(prop: String) : IllegalArgumentException("Property $prop is empty.") {
}