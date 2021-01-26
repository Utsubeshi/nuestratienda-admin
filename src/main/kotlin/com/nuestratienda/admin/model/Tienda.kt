package com.nuestratienda.admin.model

import javax.persistence.*

@Entity
data class Tienda (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTienda: Long = 0,

    @Column
    var nombre: String = "",

    @Column
    var detalle: String = ""
        ) {
}