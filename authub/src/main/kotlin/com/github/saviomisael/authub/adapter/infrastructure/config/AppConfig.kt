package com.github.saviomisael.authub.adapter.infrastructure.config

import com.github.saviomisael.authub.adapter.presentation.TimeInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig : WebMvcConfigurer {
  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(TimeInterceptor())

    super.addInterceptors(registry)
  }
}