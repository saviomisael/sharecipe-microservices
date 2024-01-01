package com.github.saviomisael.authub.adapter.infrastructure.logging

import org.slf4j.LoggerFactory
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LogHandler<T>(private val clazz: Class<T>) {
  private val logger = LoggerFactory.getLogger(clazz)

  fun logSuccessResponse(msg: String) {
    logger.info("${getNow()} - ${Thread.currentThread().name} - INFO - RESPONSE SUCCESSFULLY - $msg")
  }

  fun logExecutionTime(executionTime: Long) {
    logger.info("Request execution time: $executionTime ms")
  }

  fun logResponseException(ex: Exception) {
    logger.error("${getNow()} - ${Thread.currentThread().name} - ERROR - RESPONSE COMPLETED WITH ERRORS - ${ex.message}")
  }

  private fun getNow() = ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
}