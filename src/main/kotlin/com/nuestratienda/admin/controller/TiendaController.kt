package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.service.TiendaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException




@RestController
@RequestMapping("/api/tienda")
class TiendaController (
    var service: TiendaService
        ) {

    @PostMapping("/actualizar", produces = arrayOf("application/json"))
    fun update(@RequestBody tienda: Tienda): ResponseEntity<Any> {
        return ResponseEntity(service.update(tienda), HttpStatus.OK)
    }

    @GetMapping("/{idTienda}", produces = arrayOf("application/json"))
    fun getStoreById(@PathVariable("idTienda") idTienda: Long) : ResponseEntity<Any> {
//            val tienda: Tienda = service.getStoreById(idTienda) ?: return ResponseEntity("Registro no encontrado",HttpStatus.NOT_FOUND)
//            return ResponseEntity(tienda, HttpStatus.OK)
        try {
            return ResponseEntity(service.getStoreById(idTienda), HttpStatus.OK)
        } catch (ex: TiendaException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay registro", ex)
        }
    }

}