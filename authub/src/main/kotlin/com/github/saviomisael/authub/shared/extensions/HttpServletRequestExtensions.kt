package com.github.saviomisael.authub.shared.extensions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.util.ContentCachingRequestWrapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

fun HttpServletRequest.getUsername() = this.getAttribute("username").toString()

fun HttpServletRequest.getBody(): String = ContentCachingRequestWrapper(this).contentAsString