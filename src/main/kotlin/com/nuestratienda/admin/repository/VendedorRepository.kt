package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.Vendedor
import org.hibernate.annotations.OrderBy
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
interface VendedorRepository : CrudRepository<Vendedor, Long> {

    fun findByCorreo(email: String): Vendedor?

    @Modifying
    @Query("update Vendedor v set v.nombres = :nombres, v.apellidos = :apellidos where v.id = :id ")
    fun updateUser(@Param(value = "nombres") nombres: String,
                   @Param(value = "apellidos") apellidos: String,
                   @Param(value = "id") id: Long)

    fun findAllByOrderByIdAsc(): MutableIterable<Vendedor>

    @Modifying
    @Query("update Vendedor v set v.estaActivo = :estaActivo where v.id = :id")
    fun updateAccountState (@Param(value = "id") id: Long,
                            @Param(value = "estaActivo") estaActivo: Boolean) {

    }


}

