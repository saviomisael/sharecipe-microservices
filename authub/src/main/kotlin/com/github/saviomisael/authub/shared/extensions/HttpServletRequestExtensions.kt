package com.github.saviomisael.authub.shared.extensions

import jakarta.servlet.http.HttpServletRequest
import java.util.stream.Collectors

fun HttpServletRequest.getUsername() = this.getAttribute("username").toString()

fun HttpServletRequest.getBody() = this.reader.lines().collect(Collectors.joining())