package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.presentation.dto.ChangeUsernameDto
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.IChangeUsernameUseCase
import com.github.saviomisael.authub.shared.extensions.getUsername
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangeUsernameController @Autowired constructor(private val useCase: IChangeUsernameUseCase) : BaseController() {
  @Operation(summary = "Change username of a chef", description = "Returns 200 with the new token")
  @ApiResponses(
    value = [
      ApiResponse(responseCode = "200", description = "Username changed successfully."),
      ApiResponse(responseCode = "401", description = "Token is invalid."),
      ApiResponse(responseCode = "400", description = "New username must have must have between 2 and 255 characters.")
    ]
  )
  @PatchMapping(ApiRoutes.ChefRoutes.changeUsername)
  @Transactional
  fun changeUsername(
    @RequestBody @Valid dto: ChangeUsernameDto,
    request: HttpServletRequest
  ): ResponseEntity<ResponseDto<TokenResultDto>> {
    val token = useCase.handle(request.getUsername(), dto.newUsername)
    return ok(ResponseDto(emptyList(), token))
  }
}