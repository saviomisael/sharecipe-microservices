package com.github.saviomisael.authub.adapter.presentation.validators

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordValidator : ConstraintValidator<ValidPassword, String> {
    override fun isValid(password: String?, context: ConstraintValidatorContext?): Boolean {
        return password != null
                && password.length >= 8
                && password.length <= 255
                && password.any { it.isUpperCase() }
                && password.any { it.isLowerCase() }
                && password.any { it.isDigit() }
                && password.any { !it.isLetterOrDigit() }
    }
}