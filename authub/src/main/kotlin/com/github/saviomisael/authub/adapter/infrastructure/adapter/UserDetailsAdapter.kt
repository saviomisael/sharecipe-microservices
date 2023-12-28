package com.github.saviomisael.authub.adapter.infrastructure.adapter

import com.github.saviomisael.authub.adapter.infrastructure.dto.ChefDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsAdapter(private val chefDto: ChefDto) : UserDetails {
  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

  override fun getPassword() = chefDto.password

  override fun getUsername() = chefDto.username

  override fun isAccountNonExpired() = true

  override fun isAccountNonLocked() = true

  override fun isCredentialsNonExpired() = true

  override fun isEnabled() = true
}