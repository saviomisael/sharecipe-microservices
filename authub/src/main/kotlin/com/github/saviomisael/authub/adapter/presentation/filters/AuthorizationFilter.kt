package com.github.saviomisael.authub.adapter.presentation.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthorizationFilter @Autowired constructor(
  private val tokenService: TokenService,
  private val objectMapper: ObjectMapper,
  private val chefRepository: IChefRepository
) : OncePerRequestFilter() {
  private val privateEndpoints = listOf(ApiRoutes.ChefRoutes.refreshToken)

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    if (privateEndpoints.contains(request.requestURI)) {
      val token = request.getHeader("Authorization")

      if (token.isNullOrEmpty() || !token.startsWith("Bearer ")) {
        handleTokenInvalid(response, "Token not provided.")
        return
      }

      val decodedToken = tokenService.decodeToken(token.split(" ")[1])

      if (decodedToken == null) {
        handleTokenInvalid(response, "Token provided is invalid.")
        return
      }

      val chefExists = chefRepository.chefUsernameAlreadyExists(decodedToken.username)

      if (!chefExists) {
        handleTokenInvalid(response, "Token provided is invalid.")
        return
      }

      request.setAttribute("username", decodedToken.username)
    }

    filterChain.doFilter(request, response)
  }

  private fun handleTokenInvalid(response: HttpServletResponse, msg: String) {
    response.status = 401
    response.writer.write(objectMapper.writeValueAsString(ResponseDto<TokenResultDto>(listOf(msg), null)))
    response.contentType = MediaType.APPLICATION_JSON_VALUE
  }
}