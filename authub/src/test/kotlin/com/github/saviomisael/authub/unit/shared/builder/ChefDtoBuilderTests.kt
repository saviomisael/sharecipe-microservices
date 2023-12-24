package com.github.saviomisael.authub.unit.shared.builder

import com.github.saviomisael.authub.shared.builder.ChefDtoBuilder
import com.github.saviomisael.authub.shared.exceptions.EmptyStringException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ChefDtoBuilderTests {
    @Test
    fun `should throw a EmptyStringException when email is empty`() {
        Assertions.assertThatThrownBy {
            ChefDtoBuilder.createBuilder()
                .withEmail("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property email is empty.")
    }


    @Test
    fun `should throw a EmptyStringException when fullName is empty`() {
        Assertions.assertThatThrownBy {
            ChefDtoBuilder.createBuilder()
                .withFullName("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property fullName is empty.")
    }

    @Test
    fun `should throw a EmptyStringException when username is empty`() {
        Assertions.assertThatThrownBy {
            ChefDtoBuilder.createBuilder()
                .withUsername("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property username is empty.")
    }

    @Test
    fun `should throw a EmptyStringException when password is empty`() {
        Assertions.assertThatThrownBy {
            ChefDtoBuilder.createBuilder()
                .withPassword("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property password is empty.")
    }

    @Test
    fun `should create a Chef instance without throwing exceptions`() {
        Assertions.assertThatCode {
            val chef = ChefDtoBuilder.createBuilder()
                .withEmail("test@gmail.com")
                .withPassword("12345678")
                .withUsername("test")
                .withFullName("Test")
                .build()

            Assertions.assertThat(chef.email).isEqualTo("test@gmail.com")
            Assertions.assertThat(chef.password).isEqualTo("12345678")
            Assertions.assertThat(chef.username).isEqualTo("test")
            Assertions.assertThat(chef.fullName).isEqualTo("Test")
        }.doesNotThrowAnyException()
    }
}