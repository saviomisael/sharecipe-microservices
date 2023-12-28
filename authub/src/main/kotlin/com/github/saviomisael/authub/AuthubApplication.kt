package com.github.saviomisael.authub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthubApplication

fun main(args: Array<String>) {
  runApplication<AuthubApplication>(*args)
}
