package com.github.saviomisael.authub.shared.builder

import com.github.saviomisael.authub.shared.exceptions.EmptyStringException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ChefBuilderTests {
    @Test
    fun `should throw a EmptyStringException when email is empty`() {
        Assertions.assertThatThrownBy {
            ChefBuilder.createBuilder()
                .withEmail("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property email is empty.")
    }


    @Test
    fun `should throw a EmptyStringException when fullName is empty`() {
        Assertions.assertThatThrownBy {
            ChefBuilder.createBuilder()
                .withFullName("")
                .build()
        }
            .isInstanceOf(EmptyStringException::class.java)
            .hasMessage("Property fullName is empty.")
    }
}