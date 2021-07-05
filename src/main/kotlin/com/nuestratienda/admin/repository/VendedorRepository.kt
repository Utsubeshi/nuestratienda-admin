package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Vendedor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface VendedorRepository : CrudRepository<Vendedor, Long> {
    fun findByCorreo(email: String): Vendedor?

    @Query("update Vendedor v set v.nombres = :nombres, v.apellidos = :apellidos where v.id = :id ")
    fun updateUser(@Param(value = "nombres") nombres: String,
                   @Param(value = "apellidos") apellidos: String,
                   @Param(value = "id") id: Long)

}