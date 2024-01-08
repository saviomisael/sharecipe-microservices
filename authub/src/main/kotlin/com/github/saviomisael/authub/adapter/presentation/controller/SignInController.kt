package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.dto.SignInCredentialsDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.ISignInUseCase
import com.github.saviomisael.authub.shared.exceptions.CredentialsInvalidException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController @Autowired constructor(private val signInUseCase: ISignInUseCase) : BaseController() {
  private val logger = LogHandler(SignInController::class.java)

  @Transactional
  @Operation(summary = "Sign in into sharecipe", description = "Returns 200 if successfully")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Return your JWT token."),
      ApiResponse(responseCode = "401", description = "Username or password are wrong."),
      ApiResponse(
        responseCode = "400",
        description = "Validation failed for the body.",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseDto::class))]
      )
    ]
  )
  @PostMapping(ApiRoutes.ChefRoutes.signIn, produces = [MediaType.APPLICATION_JSON_VALUE])
  fun signIn(@Valid @RequestBody dto: SignInCredentialsDto): ResponseEntity<ResponseDto<TokenResultDto>> {
    try {
      val token = signInUseCase.handle(dto.username, dto.password)

      logger.logSuccessResponse("Sign in successfully for ${token.username}")
      return ok(ResponseDto(emptyList(), token))
    } catch (ex: CredentialsInvalidException) {
      logger.logResponseException(ex)
      return unauthorized(ResponseDto(listOf(ex.message), null))
    }
  }
}