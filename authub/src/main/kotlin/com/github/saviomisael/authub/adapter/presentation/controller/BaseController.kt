package com.github.saviomisael.authub.adapter.presentation.controller

import org.springframework.http.ResponseEntity

abstract class BaseController {
    fun <T> created(body: T): ResponseEntity<T> {
        return ResponseEntity.status(201).body(body)
    }

    fun <T> unprocessableEntity(body: T): ResponseEntity<T> {
        return ResponseEntity.status(422).body(body)
    }
}