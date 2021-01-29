package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.repository.VendedorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional

@Service
open class VendedorService (
    var bCryptPasswordEncoder: BCryptPasswordEncoder,
    var repository: VendedorRepository
        ) : UserDetailsService{

    override fun loadUserByUsername(email: String?): UserDetails {
        val vendedor: Vendedor = email?.let { repository
            .findByCorreo(it)} ?: throw UsernameNotFoundException(email)
        return vendedor
    }

    @Transactional
    open fun saveNewUser(vendedor: Vendedor): String {
        vendedor.password = bCryptPasswordEncoder
            .encode(vendedor.password)
        return repository.save(vendedor).id.toString()
    }
}