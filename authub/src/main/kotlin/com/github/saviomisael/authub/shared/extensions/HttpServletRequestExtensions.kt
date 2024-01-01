package com.github.saviomisael.authub.shared.extensions

import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.getUsername() = this.getAttribute("username").toString()