package com.nuestratienda.admin.model

import org.hibernate.annotations.Parent
import java.util.*
import javax.persistence.*


class PasswordResetToken(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long = 0,

    private var token: String,

    @OneToOne(targetEntity = Vendedor::class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private var user: Vendedor,

    private var expiryDate: Date = Date()
){

    private fun calculateExpiryDate(expiryTimeInMinutes: Int): Date {
        val cal = Calendar.getInstance()
        cal.timeInMillis = Date().time
        cal.add(Calendar.MINUTE, expiryTimeInMinutes)
        return Date(cal.time.time)
    }

init {
    this.expiryDate = calculateExpiryDate(EXPIRATION)
}


    fun PasswordResetToken(token: String, user: Vendedor) {
        this.token = token
        this.user = user
        this.expiryDate = calculateExpiryDate(EXPIRATION)
    }

    fun updateToken(token: String) {
        this.token = token
        this.expiryDate = calculateExpiryDate(EXPIRATION)
    }

    override fun toString(): String {
        return "PasswordResetToken(id=$id, token='$token', user=$user, expiryDate=$expiryDate)"
    }

    companion object {
        val EXPIRATION = 60 * 24
    }
}