package com.github.saviomisael.authub.shared.extensions

import com.github.saviomisael.authub.adapter.infrastructure.adapter.CachedBodyHttpServletRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.util.StreamUtils
import org.springframework.web.util.ContentCachingRequestWrapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

fun HttpServletRequest.getUsername() = this.getAttribute("username").toString()

fun HttpServletRequest.getBody(): String =
  BufferedReader(InputStreamReader(inputStream)).lines().collect(Collectors.joining())