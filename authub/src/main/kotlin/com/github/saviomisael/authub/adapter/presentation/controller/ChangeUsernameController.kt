package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import com.github.saviomisael.authub.adapter.presentation.dto.ChangeUsernameDto
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.IChangeUsernameUseCase
import com.github.saviomisael.authub.shared.exceptions.UsernameAlreadyExistsException
import com.github.saviomisael.authub.shared.extensions.getUsername
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangeUsernameController @Autowired constructor(private val useCase: IChangeUsernameUseCase) : BaseController() {
  private val logHandler = LogHandler(ChangeUsernameController::class.java)

  @Transactional
  @Operation(summary = "Change username of a chef", description = "Returns 200 with the new token")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Username changed successfully."),
      ApiResponse(responseCode = "401", description = "Token is invalid."),
      ApiResponse(
        responseCode = "400",
        description = "Validation failed for the body.",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseDto::class))]
      )
    ]
  )
  @PatchMapping(ApiRoutes.ChefRoutes.changeUsername)
  fun changeUsername(
    @RequestBody @Valid dto: ChangeUsernameDto,
    request: HttpServletRequest
  ): ResponseEntity<ResponseDto<TokenResultDto>> {
    try {
      val token = useCase.handle(request.getUsername(), dto.newUsername)

      logHandler.logSuccessResponse("username ${request.getUsername()} changed to ${dto.newUsername}")
      return ok(ResponseDto(emptyList(), token))
    } catch (ex: UsernameAlreadyExistsException) {
      logHandler.logResponseException(ex)
      return unprocessableEntity(ResponseDto(listOf(ex.message), null))
    }
  }
}