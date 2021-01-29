package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Tienda
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tienda")
class TiendaController (

        ) {

    fun update(@RequestBody tienda: Tienda) {
        //TODO
    }
}