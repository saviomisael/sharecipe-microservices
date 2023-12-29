package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.IGenerateRefreshTokenUseCase
import com.github.saviomisael.authub.shared.exceptions.TokenInvalidException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class GenerateRefreshTokenController @Autowired constructor(private val generateRefreshTokenUseCase: IGenerateRefreshTokenUseCase) :
  BaseController() {
  private val logger = LoggerFactory.getLogger(GenerateRefreshTokenController::class.java)


  @Operation(summary = "Validate tokens and generate a new token", description = "Returns 201 with the new token")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "201", description = "Account successfully created."),
      ApiResponse(responseCode = "401", description = "Token invalid."),
    ]
  )
  @PostMapping(ApiRoutes.ChefRoutes.refreshToken)
  fun generateRefreshToken(
    @RequestHeader(
      value = "Authorization",
      required = true
    ) token: String
  ): ResponseEntity<ResponseDto<TokenResultDto>> {
    return try {
      val tokenDto = generateRefreshTokenUseCase.handle(token.split(" ")[1])
      logger.info("RESPONSE. Generate refresh token for ${tokenDto.username}")
      created(ResponseDto(emptyList(), tokenDto))
    } catch (ex: TokenInvalidException) {
      unauthorized(ResponseDto(listOf(ex.message), null))
    }
  }
}