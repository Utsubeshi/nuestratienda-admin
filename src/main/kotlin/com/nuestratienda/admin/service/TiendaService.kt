package com.nuestratienda.admin.service

import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.repository.TiendaRepository

class TiendaService (
    var repository: TiendaRepository) {

    fun update(tienda: Tienda) {
        repository.save(tienda)
    }
}