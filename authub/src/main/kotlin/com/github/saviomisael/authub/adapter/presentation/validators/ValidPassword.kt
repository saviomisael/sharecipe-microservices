package com.github.saviomisael.authub.adapter.presentation.validators

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [PasswordValidator::class])
annotation class ValidPassword(
  val message: String =
    "provide a password with a length between 8 and 255 characters, and contains an uppercase letter, a lowercase letter, a number and a special characters",
  val groups: Array<KClass<out Any>> = [],
  val payload: Array<KClass<out Payload>> = []
)