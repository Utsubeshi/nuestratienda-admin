package com.nuestratienda.admin.model

import javax.persistence.*

@Entity
data class Vendedor (
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column
    var nombres: String = "",

    @Column
    var apellidos: String = "",

    @Column
    var password: String = "",

    @Column
    var correo: String = "",

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idTienda")
    var tienda: Tienda = Tienda(),

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idSuscripcion")
    var suscripcion: Suscripcion = Suscripcion()
    ) { }