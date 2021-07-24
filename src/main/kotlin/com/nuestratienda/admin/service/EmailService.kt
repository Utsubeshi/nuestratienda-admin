package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Vendedor
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest


@Service
class EmailService(javaMailSender: JavaMailSender) {

    private val javaMailSender: JavaMailSender

    fun sendMail(email: SimpleMailMessage) {
        javaMailSender.send(email)
    }

    fun createMailResetPassword(
        email: String,
        subject: String,
        token: String,
        user: Vendedor,
        request: HttpServletRequest
    ): SimpleMailMessage {
        val email  = SimpleMailMessage()
        val url = "${request.requestURL}/api/vendedor/cambiarpassword?token=$token"
        println(url)
        val body = "Link para resetear contraseña: $url"
        email.setSubject(subject)
        email.setText(body)
        email.setTo(user.correo)
        email.setFrom("admin@nuestratienda.com.pe")
        return email
    }

    init {
        this.javaMailSender = javaMailSender
    }
}