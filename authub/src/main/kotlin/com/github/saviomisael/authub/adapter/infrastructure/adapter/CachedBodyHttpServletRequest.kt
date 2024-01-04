package com.github.saviomisael.authub.adapter.infrastructure.adapter

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.util.StreamUtils
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class CachedBodyHttpServletRequest(private val request: HttpServletRequest) : HttpServletRequestWrapper(request) {
  private val cachedBody = StreamUtils.copyToByteArray(request.inputStream)

  override fun getInputStream(): ServletInputStream = CachedBodyServletInputStream(ByteArrayInputStream(cachedBody))

  override fun getReader(): BufferedReader = BufferedReader(InputStreamReader(ByteArrayInputStream(cachedBody)))

  private class CachedBodyServletInputStream(private val cachedBodyInputStream: ByteArrayInputStream) :
    ServletInputStream() {
    override fun read(): Int = cachedBodyInputStream.read()

    override fun isFinished(): Boolean = cachedBodyInputStream.available() == 0

    override fun isReady(): Boolean = true

    override fun setReadListener(readListener: ReadListener) {
      throw UnsupportedOperationException()
    }
  }
}