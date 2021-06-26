package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.repository.TiendaRepository
import org.springframework.stereotype.Service

@Service
class TiendaService (
    var repository: TiendaRepository) {

    fun update(tienda: Tienda) = repository.save(tienda)

//    fun getStoreById(idTienda: Long): Tienda? {
//       val tienda: Tienda? = repository.findByIdTienda(idTienda)
//        if (tienda != null) return tienda
//       return null
//    }

    fun getStoreById(idTienda: Long): Tienda? {
        return repository.findByIdTienda(idTienda)
    }
}