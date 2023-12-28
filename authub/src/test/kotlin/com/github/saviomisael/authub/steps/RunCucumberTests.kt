package com.github.saviomisael.authub.steps

import io.cucumber.junit.platform.engine.Constants
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ContextConfiguration

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.github.saviomisael.authub.steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(
  key = Constants.FEATURES_PROPERTY_NAME,
  value = "src/test/resources/com/github/saviomisael/authub/features"
)
@ContextConfiguration
@CucumberContextConfiguration
class RunCucumberTests