package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import com.github.saviomisael.authub.adapter.presentation.dto.ChangePasswordDto
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.usecases.IChangePasswordUseCase
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
class ChangePasswordController @Autowired constructor(private val useCase: IChangePasswordUseCase) : BaseController() {
  private val logger = LogHandler(ChangePasswordController::class.java)

  @Transactional
  @PatchMapping(ApiRoutes.ChefRoutes.changePassword)
  @Operation(summary = "Change your password.", description = "Returns 204 if successfully")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "204", description = "Changed password successfully."),
      ApiResponse(
        responseCode = "400",
        description = "Validation failed for the body.",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseDto::class))]
      ),
      ApiResponse(
        responseCode = "401",
        description = "chef is not authorized to change his password",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseDto::class))]
      )
    ]
  )
  fun changePassword(
    @Valid @RequestBody dto: ChangePasswordDto,
    request: HttpServletRequest
  ): ResponseEntity<Any> {
    useCase.handle(request.getUsername(), dto.password)

    logger.logSuccessResponse("Password changed for ${request.getUsername()}")
    return noContent()
  }
}