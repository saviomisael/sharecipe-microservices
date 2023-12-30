package com.github.saviomisael.authub.adapter.infrastructure.config

import com.github.saviomisael.authub.adapter.infrastructure.adapter.UserDetailsServiceAdapter
import com.github.saviomisael.authub.adapter.infrastructure.persistence.ChefDtoRepository
import com.github.saviomisael.authub.adapter.presentation.filters.AuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
class AuthConfig(
  @Autowired private val repository: ChefDtoRepository,
  private val authorizationFilter: AuthorizationFilter
) {
  private val swaggerEndpoints = arrayOf(
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui.html",
    "/webjars/**",
    "/v3/api-docs/**",
    "/api/public/**",
    "/api/public/authenticate",
    "/actuator/*",
    "/swagger-ui/**"
  )

  private val publicEndpoints = arrayOf("/api/**")

  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @Bean
  fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
    configuration.authenticationManager

  @Bean
  fun userDetailsService(): UserDetailsService = UserDetailsServiceAdapter(repository)

  @Bean
  fun authenticationProvider(): AuthenticationProvider {
    val authenticationProvider = DaoAuthenticationProvider()
    authenticationProvider.setUserDetailsService(userDetailsService())
    authenticationProvider.setPasswordEncoder(passwordEncoder())

    return authenticationProvider
  }

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
    http
      .httpBasic {
        it.disable()
      }
      .csrf { it.disable() }
      .sessionManagement {
        it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      }.authorizeHttpRequests {
        it.requestMatchers(
          *swaggerEndpoints,
          *publicEndpoints
        ).permitAll()
        it.anyRequest().authenticated()
      }
      .authenticationProvider(authenticationProvider())
      .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter::class.java)
      .build()

  @Bean
  fun corsFilter(): CorsFilter {
    val config = CorsConfiguration()
    config.addAllowedHeader("*")
    config.addAllowedOriginPattern("*")
    config.addAllowedMethod("*")
    config.allowCredentials = true

    val source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**", config)
    return CorsFilter(source)
  }
}