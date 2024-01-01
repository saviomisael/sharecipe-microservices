package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.ISaveChefCredentialsUseCase
import com.github.saviomisael.authub.shared.exceptions.EmailAlreadyUsedException
import com.github.saviomisael.authub.shared.exceptions.UsernameAlreadyExistsException
import com.github.saviomisael.authub.shared.extensions.toChef
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SaveChefCredentialsController(@Autowired private val saveChefCredentialsUseCase: ISaveChefCredentialsUseCase) :
  BaseController() {
  private val logger = LogHandler(SaveChefCredentialsController::class.java)

  @Operation(summary = "Creates a chef account", description = "Returns 201 if successfully")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "201", description = "Account successfully created."),
      ApiResponse(responseCode = "422", description = "Username or email already used."),
      ApiResponse(
        responseCode = "400",
        description = "Validation failed for the body. It returns a dictionary",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = Map::class))]
      )
    ]
  )
  @PostMapping(ApiRoutes.ChefRoutes.createChefAccount)
  @Transactional
  fun createChef(@Valid @RequestBody dto: CreateChefDto): ResponseEntity<ResponseDto<TokenResultDto>> {
    try {
      val chefSaved = saveChefCredentialsUseCase.handle(dto.toChef())

      logger.logSuccessResponse("Chef created for ${chefSaved.username}")
      return created(ResponseDto(emptyList<String>(), chefSaved))
    } catch (ex: UsernameAlreadyExistsException) {
      return handleException(ex)
    } catch (ex: EmailAlreadyUsedException) {
      return handleException(ex)
    }
  }

  private fun handleException(ex: Exception): ResponseEntity<ResponseDto<TokenResultDto>> {
    logger.logResponseException(ex)

    return unprocessableEntity(ResponseDto(listOf(ex.message), null))
  }
}