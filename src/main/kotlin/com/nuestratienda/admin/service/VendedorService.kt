package com.nuestratienda.admin.service

import com.google.gson.Gson
import com.nuestratienda.admin.model.PaymentDetails
import com.nuestratienda.admin.model.Suscripcion
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
    var repository: VendedorRepository,
    val culqiAPI: CulqiAPI
        ) : UserDetailsService{

    override fun loadUserByUsername(email: String?): UserDetails {
        val vendedor: Vendedor = email?.let { repository
            .findByCorreo(it)} ?: throw UsernameNotFoundException(email)
        return vendedor
    }

    @Transactional
    open fun saveNewUser(vendedor: Vendedor): String {
        val v: Vendedor? = repository.findByCorreo(vendedor.correo)
        return if (v != null){
            "El correo ya esta registrado"
        } else {
            newUserPayment(vendedor)
            vendedor.password = bCryptPasswordEncoder
                .encode(vendedor.password)
            repository.save(vendedor).id.toString()
        }
    }

    fun newUserPayment(vendedor: Vendedor): Map<String, Any> {
        val payment = PaymentDetails(
            amount = "39900",
            currency_code = "PEN",
            email = vendedor.correo,
            source_id = vendedor.suscripcion.token)
        val response = culqiAPI.getPayment(payment)
        var map: Map<String, Any> = HashMap()
        map = Gson().fromJson(response, map.javaClass)
        print(map)
        return map

    }


}