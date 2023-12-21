package com.github.saviomisael.authub.adapter.infrastructure.adapter

import com.github.saviomisael.authub.adapter.infrastructure.persistence.ChefDtoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceAdapter(@Autowired private val repository: ChefDtoRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username)
            ?: throw UsernameNotFoundException("Username $username not found.")

        return UserDetailsAdapter(user)
    }
}