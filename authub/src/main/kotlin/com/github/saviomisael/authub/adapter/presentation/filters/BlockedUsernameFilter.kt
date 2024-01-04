package com.github.saviomisael.authub.adapter.presentation.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import com.github.saviomisael.authub.adapter.infrastructure.service.BlockedUsernameService
import com.github.saviomisael.authub.adapter.infrastructure.service.TokenService
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.shared.extensions.getBody
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class BlockedUsernameFilter @Autowired constructor(
  private val blockedUsernameService: BlockedUsernameService,
  private val tokenService: TokenService
) :
  OncePerRequestFilter() {
  private val logHandler = LogHandler(BlockedUsernameFilter::class.java)

  private val strategy = mapOf(
    ApiRoutes.ChefRoutes.createChefAccount to CreateChefAccountValidation(),
    ApiRoutes.ChefRoutes.refreshToken to TokenValidation(),
    ApiRoutes.ChefRoutes.changePassword to TokenValidation(),
    ApiRoutes.ChefRoutes.changeUsername to ChangeUsernameValidation()
  )

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    if (!strategy.containsKey(request.requestURI)) {
      filterChain.doFilter(request, response)
      return
    }

    val selectedStrategy = strategy[request.requestURI]

    if (selectedStrategy == null) {
      filterChain.doFilter(request, response)
      return
    }

    try {
      val isValidUsername = selectedStrategy.isValidUsername(request, blockedUsernameService, tokenService)

      if (isValidUsername) {
        filterChain.doFilter(request, response)
        return
      }
    } catch (ex: Exception) {
      logHandler.logResponseException(ex)
      response.status = 500
      return
    }

    logHandler.logResponseException(RuntimeException("Username is not available."))
    response.status = 422
    response.writer.write(ObjectMapper().writeValueAsString(ResponseDto(listOf("Username is not available."), null)))
    response.contentType = MediaType.APPLICATION_JSON_VALUE
    return
  }

  internal interface ValidateUsername {
    val objectMapper: ObjectMapper
      get() = ObjectMapper().registerKotlinModule()

    fun isValidUsername(
      request: HttpServletRequest,
      blockedUsernameService: BlockedUsernameService,
      tokenService: TokenService
    ): Boolean
  }

  internal class CreateChefAccountValidation :
    ValidateUsername {
    override fun isValidUsername(
      request: HttpServletRequest,
      blockedUsernameService: BlockedUsernameService,
      tokenService: TokenService
    ): Boolean {
      val usernameToValidate = objectMapper.readTree(request.getBody()).at("/username").asText()

      return blockedUsernameService.isAvailableUsername(usernameToValidate)
    }
  }

  internal class TokenValidation : ValidateUsername {
    override fun isValidUsername(
      request: HttpServletRequest,
      blockedUsernameService: BlockedUsernameService,
      tokenService: TokenService
    ): Boolean {
      val token = request.getHeader("Authorization").split(" ")[1]

      val username = tokenService.decodeToken(token)?.username ?: return false

      return blockedUsernameService.isAvailableUsername(username)
    }
  }

  internal class ChangeUsernameValidation : ValidateUsername {
    override fun isValidUsername(
      request: HttpServletRequest,
      blockedUsernameService: BlockedUsernameService,
      tokenService: TokenService
    ): Boolean {
      var isValid = TokenValidation().isValidUsername(request, blockedUsernameService, tokenService)

      val usernameToValidate = objectMapper.readTree(request.getBody()).at("/newUsername").asText()

      isValid = isValid && blockedUsernameService.isAvailableUsername(usernameToValidate)

      return isValid
    }

  }
}