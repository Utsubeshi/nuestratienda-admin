package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Tienda
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional
interface TiendaRepository : CrudRepository<Tienda, Long> {

    //fun findByIdTienda (idTienda: Long): Tienda?

    @Modifying
    @Query("update Tienda t set " +
            "t.nombre = :nombre," +
            "t.detalle = :detalle," +
            "t.logoUrl = :logoUrl," +
            "t.direccion = :direccion," +
            "t.color1 = :color1," +
            "t.color2 = :color2")
    fun updateTienda(@Param(value = "nombre") nombre: String,
                     @Param(value = "detalle") detalle: String,
                     @Param(value = "logoUrl") logoUrl: String,
                     @Param(value = "direccion") direccion: String,
                     @Param(value = "color1") color1: String,
                     @Param(value = "color2") color2: String)
}