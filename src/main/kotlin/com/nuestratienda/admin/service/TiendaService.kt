package com.nuestratienda.admin.service

import com.nuestratienda.admin.controller.exception.StoreNotFoundException
import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.repository.TiendaRepository
import org.springframework.stereotype.Service

@Service
class TiendaService (
    var repository: TiendaRepository) {

    //fun update(tienda: Tienda) = repository.save(tienda)

    fun update(tienda: Tienda): String {
        val optionalT = repository.findById(tienda.idTienda)
        if (!optionalT.isPresent()) throw StoreNotFoundException()
        val t = optionalT.get()
        repository.updateTienda(
            if (tienda.nombre.isBlank()) t.nombre else tienda.nombre,
            if (tienda.detalle.isBlank()) t.detalle else tienda.detalle,
            if (tienda.logoUrl.isBlank()) t.logoUrl else tienda.logoUrl,
            if (tienda.direccion.isBlank()) t.direccion else tienda.direccion,
            if (tienda.color1.isBlank()) t.color1 else tienda.color1,
            if (tienda.color2.isBlank()) t.color2 else tienda.color2)
        return "Datos actualizados"
    }

//    fun getStoreById(idTienda: Long): Tienda? {
//       val tienda: Tienda? = repository.findByIdTienda(idTienda)
//        if (tienda != null) return tienda
//       return null
//    }

    fun getStoreById(idTienda: Long): Tienda? {
        return repository.findById(idTienda).get()
    }
}