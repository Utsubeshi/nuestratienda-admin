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
import java.util.*
import kotlin.collections.HashMap

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
        if (v != null){
            return "El correo ya esta registrado"
        } else {
            val apiResponse = newUserPayment(vendedor)
            if (apiResponse.isSuccessful) {
                vendedor.password = bCryptPasswordEncoder
                    .encode(vendedor.password)
                return repository.save(vendedor).id.toString()
            }
            return  apiResponse.mensaje
        }
    }

    fun newUserPayment(vendedor: Vendedor): RespuestaCulqui {
        val payment = PaymentDetails(
            amount = "39900",
            currency_code = "PEN",
            email = vendedor.correo,
            source_id = vendedor.suscripcion.token)
        val response = culqiAPI.getPayment(payment)
        var map: Map<String, Any> = HashMap()
        map = Gson().fromJson(response, map.javaClass)
        val respuestaCulqui = RespuestaCulqui()
        if (map["object"] == "error") {
            respuestaCulqui.mensaje = map["user_message"].toString()
        } else {
            respuestaCulqui.isSuccessful = true
            respuestaCulqui.mensaje = "Se efectuo el pago con exito."
        }
        return respuestaCulqui
    }

    fun getUserById(id: Long): Vendedor = repository.findById(id).get()

}

class RespuestaCulqui(
    var isSuccessful: Boolean = false,
    var mensaje: String = "",
    var id: String = ""
){
    override fun toString(): String {
        return "RespuestaCulqui(isSuccessful=$isSuccessful, mensaje='$mensaje', id='$id')"
    }
}