package com.github.saviomisael.authub.adapter.infrastructure.dto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "chefs")
class ChefDto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: String

    @Column(unique = false, nullable = false, length = 255)
    lateinit var fullName: String

    @Column(unique = false, nullable = false, length = 255)
    lateinit var username: String

    @Column(nullable = false, length = 255)
    lateinit var password: String

    @Column(nullable = false, length = 255)
    lateinit var email: String
}