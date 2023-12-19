package com.github.saviomisael.authub.adapter.infrastructure.repository

import com.github.saviomisael.authub.adapter.infrastructure.persistence.ChefDtoRepository
import com.github.saviomisael.authub.core.domain.entity.Chef
import com.github.saviomisael.authub.core.domain.repository.IChefRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ChefRepository(@Autowired val chefDtoRepository: ChefDtoRepository) : IChefRepository {
    override fun saveChefCredentials(chef: Chef): Chef {
        TODO("Not yet implemented")
        TODO("Create Builder and Adapter")
    }
}