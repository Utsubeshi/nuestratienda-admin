package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.service.TiendaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tienda")
class TiendaController (
    var service: TiendaService
        ) {

    @PostMapping("/actualizar")
    fun update(@RequestBody tienda: Tienda) {
        //TODO
    }

    @GetMapping("/{idTienda}", produces = arrayOf("application/json"))
    //fun getStoreById( idTienda: Long) : Tienda = service.getStoreById(idTienda)
    fun getStoreById(@PathVariable("idTienda") idTienda: Long) : ResponseEntity<Any> {
        val tienda: Tienda = service.getStoreById(idTienda) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(tienda, HttpStatus.OK)
        //return ResponseEntity<Any>(service.getStoreById(idTienda), HttpStatus.OK)
    }

}