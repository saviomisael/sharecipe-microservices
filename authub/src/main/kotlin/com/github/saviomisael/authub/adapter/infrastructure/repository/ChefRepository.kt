package com.github.saviomisael.authub.adapter.infrastructure.repository

import com.github.saviomisael.authub.adapter.infrastructure.persistence.ChefDtoRepository
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import com.github.saviomisael.authub.shared.extensions.toChef
import com.github.saviomisael.authub.shared.extensions.toChefDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ChefRepository(@Autowired val chefDtoRepository: ChefDtoRepository) : IChefRepository {
    override fun saveChefCredentials(chef: Chef): Chef {
        return chefDtoRepository.save(chef.toChefDto()).toChef()
    }

    override fun chefUsernameAlreadyExists(username: String): Boolean {
        return chefDtoRepository.findByUsername(username) != null
    }

    override fun chefEmailAlreadyUsed(email: String) = chefDtoRepository.findByEmail(email) != null
}