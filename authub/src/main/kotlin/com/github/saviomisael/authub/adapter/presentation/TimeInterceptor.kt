package com.github.saviomisael.authub.adapter.presentation

import com.github.saviomisael.authub.adapter.infrastructure.logging.LogHandler
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

class TimeInterceptor : HandlerInterceptor {
  private val logger = LogHandler(TimeInterceptor::class.java)
  private val startTimeConstant = "START_TIME"

  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    request.setAttribute(startTimeConstant, System.currentTimeMillis())
    return super.preHandle(request, response, handler)
  }

  override fun afterCompletion(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any,
    ex: Exception?
  ) {
    val startTime = request.getAttribute(startTimeConstant) as Long
    val elapsedTime = System.currentTimeMillis() - startTime
    logger.logExecutionTime(elapsedTime)

    super.afterCompletion(request, response, handler, ex)
  }
}