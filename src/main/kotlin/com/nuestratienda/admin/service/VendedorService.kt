package com.nuestratienda.admin.service

import com.google.gson.Gson
import com.nuestratienda.admin.controller.exception.UserNotFoundException
import com.nuestratienda.admin.model.*
import com.nuestratienda.admin.repository.SuscripcionRepository
import com.nuestratienda.admin.repository.VendedorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap

@Service
open class VendedorService (
    var bCryptPasswordEncoder: BCryptPasswordEncoder,
    var repository: VendedorRepository,
    var suscripcionRepository: SuscripcionRepository,
    //var passwordResetTokenRepository: PasswordResetTokenRepository,
    val culqiAPI: CulqiAPI
    //val emailservice: EmailService
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
            return USUARIOEXISTE
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

    fun updateUser(vendedor: Vendedor): String {
        val optionalV = repository.findById(vendedor.id)
        //if (!optionalV.isPresent()) throw ApiException("User not found", HttpStatus.NOT_FOUND)
        if (!optionalV.isPresent) throw UserNotFoundException()
        val v = optionalV.get()
        //if (v.nombres.isBlank()) return
        repository.updateUser(
          if (vendedor.nombres.isBlank()) v.nombres else vendedor.nombres ,
          if (vendedor.apellidos.isBlank()) v.apellidos else vendedor.apellidos,
          vendedor.id)
        return DATOSACTUALIZADOS
    }

    fun newUserPayment(vendedor: Vendedor): RespuestaCulqui {
        val payment = PaymentDetails(
            amount = "39900",
            currency_code = "PEN",
            email = vendedor.correo,
            source_id = vendedor.suscripcion.token)
        return respuestaCulqui(payment)
    }

    private fun respuestaCulqui(payment: PaymentDetails): RespuestaCulqui {
        val response = culqiAPI.getPayment(payment)
        var map: Map<String, Any> = HashMap()
        map = Gson().fromJson(response, map.javaClass)
        val respuestaCulqui = RespuestaCulqui()
        if (map["object"] == "error") {
            respuestaCulqui.mensaje = map["user_message"].toString()
        } else {
            respuestaCulqui.isSuccessful = true
            respuestaCulqui.mensaje = PAGOEXITOSO
        }
        return respuestaCulqui
    }

    @Transactional
    open fun renewSuscripcion (suscripcion: Suscripcion) : String {
        val apiResponse = paySuscripcion(suscripcion)
        if (apiResponse.isSuccessful) {
            suscripcionRepository.save(suscripcion)
            return RENOVARSUB
        }
        return apiResponse.mensaje
    }

    fun paySuscripcion(suscripcion: Suscripcion): RespuestaCulqui {
        val payment = PaymentDetails(
            amount = PRECIOPLAN ,
            currency_code = "PEN",
            email = "renovacion@nta.com",
            source_id = suscripcion.token
        )
        return respuestaCulqui(payment)
    }

    fun getUserById(id: Long): Vendedor {
        var vendedor = Vendedor()
        try {
            vendedor = repository.findById(id).get()
            vendedor.password = ""
        } catch (e: Exception) { }
        return vendedor
    }

    fun getVendedores(): MutableIterable<Vendedor> {
        //val vendodores = repository.findAll()
        return repository.findAllByOrderByIdAsc()
    }

    fun updateAccountState(vendedor: Vendedor): String {
        val optionalV = repository.findById(vendedor.id)
        if (!optionalV.isPresent) throw UserNotFoundException()
        repository.updateAccountState(vendedor.id, vendedor.estaActivo)
        if (vendedor.estaActivo) return ACTIVARCUENTA else return DESACTIVARCUENTA
    }

//    fun resetPassword(email: String, request: HttpServletRequest): String {
//        val v = repository.findByCorreo(email) ?: throw UserNotFoundException()
//        val token = UUID.randomUUID().toString()
//        createPasswordResetTokenForUser(v, token)
//        emailservice.sendMail(emailservice.createMailResetPassword(email, "Resetear contraseña", token, v, request))
//        return "Hemos enviado un correo para resetear la contraseña"
//    }
//
//    fun createPasswordResetTokenForUser(user: Vendedor, token: String) {
//        val myToken = PasswordResetToken(token = token, user = user)
//        println(myToken.toString())
//        passwordResetTokenRepository.save(myToken)
//
//    }

    companion object {
        val ACTIVARCUENTA = "Cuenta Activada"
        val DESACTIVARCUENTA = "Cuenta Desactivada"
        val RENOVARSUB = "Suscripcion renovada"
        val PRECIOPLAN = "39900"
        val PAGOEXITOSO = "Se efectuo el pago con exito."
        val DATOSACTUALIZADOS = "Datos actualizados"
        val USUARIOEXISTE = "El correo ya esta registrado"
    }
}

