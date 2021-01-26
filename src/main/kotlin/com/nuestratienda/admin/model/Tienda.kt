package com.nuestratienda.admin.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Tienda (
    @Id
    @GeneratedValue
    var idTienda: Long = 0,

    @Column
    var nombre: String = "",

    @Column
    var detalle: String = ""
        ) {
}