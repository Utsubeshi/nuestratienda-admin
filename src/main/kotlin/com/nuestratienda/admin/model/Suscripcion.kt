package com.nuestratienda.admin.model

import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Suscripcion (
    @Id
    @GeneratedValue
    var idSuscripcion: Long = 0,

    @Column
    var token: String = "",

    @Column
    var fechaInicio: Date = Date() ){

}


