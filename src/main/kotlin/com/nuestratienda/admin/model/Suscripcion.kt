package com.nuestratienda.admin.model

import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Suscripcion (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var idSuscripcion: Long = 0,

    @Column
    var token: String = "",

    @Column
    var fechaInicio: Date = Date() ){

}


