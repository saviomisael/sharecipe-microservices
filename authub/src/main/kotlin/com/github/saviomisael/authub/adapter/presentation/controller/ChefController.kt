package com.github.saviomisael.authub.adapter.presentation.controller

import com.github.saviomisael.authub.adapter.presentation.dto.CreateChefDto
import com.github.saviomisael.authub.adapter.presentation.dto.ResponseDto
import com.github.saviomisael.authub.core.domain.dto.TokenResultDto
import com.github.saviomisael.authub.core.domain.usecases.ISaveChefCredentialsUseCase
import com.github.saviomisael.authub.shared.exceptions.EmailAlreadyUsedException
import com.github.saviomisael.authub.shared.exceptions.UsernameAlreadyExistsException
import com.github.saviomisael.authub.shared.extensions.toChef
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChefController(@Autowired private val saveChefCredentialsUseCase: ISaveChefCredentialsUseCase) : BaseController() {
    @PostMapping("/api/v1/chefs")
    fun createChef(@Valid @RequestBody dto: CreateChefDto): ResponseEntity<ResponseDto<TokenResultDto>> {
        try {
            val chefSaved = saveChefCredentialsUseCase.handle(dto.toChef());

            return created(ResponseDto(emptyList<String>(), chefSaved))
        } catch (ex: UsernameAlreadyExistsException) {
            return handleException(ex)
        } catch (ex: EmailAlreadyUsedException) {
            return handleException(ex)
        }
    }

    private fun <T> handleException(ex: Exception) = unprocessableEntity(ResponseDto<T>(listOf(ex.message), null))
}