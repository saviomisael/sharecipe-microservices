package com.github.saviomisael.authub.adapter.presentation.dto

data class ResponseDto<T>(val errors: List<String?>, val data: T?) {
}