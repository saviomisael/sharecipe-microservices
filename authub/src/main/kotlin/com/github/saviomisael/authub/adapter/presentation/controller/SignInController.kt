package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.adapter.presentation.dto.SignInCredentialsDto
import com.github.saviomisael.authub.adapter.presentation.v1.ApiRoutes
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.ISignInUseCase
import com.github.saviomisael.authub.shared.exceptions.CredentialsInvalidException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController @Autowired constructor(private val signInUseCase: ISignInUseCase) : BaseController() {
    @PostMapping(ApiRoutes.ChefRoutes.signIn)
    fun signIn(@Valid @RequestBody dto: SignInCredentialsDto): ResponseEntity<ResponseDto<TokenResultDto>> {
        try {
            val token = signInUseCase.handle(dto.username, dto.password)

            return ok(ResponseDto(emptyList(), token))
        } catch (ex: CredentialsInvalidException) {
            return unauthorized(ResponseDto(listOf(ex.message), null))
        }
    }
}