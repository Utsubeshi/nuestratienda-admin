package com.nuestratienda.admin.controller

import com.nuestratienda.admin.controller.exception.ApiRequestException
import com.nuestratienda.admin.controller.exception.StoreNotFoundException
import com.nuestratienda.admin.model.Tienda
import com.nuestratienda.admin.service.TiendaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.server.ResponseStatusException
import java.lang.RuntimeException


@RestController
@RequestMapping("/api/tienda")
class TiendaController (
    var service: TiendaService
        ) {

    @PostMapping("/actualizar", produces = arrayOf("application/json"))
    fun update(@RequestBody tienda: Tienda): ResponseEntity<Any> {
        val mensaje : MutableMap< String, String> = HashMap()
        mensaje.put("mensaje", service.update(tienda))
        return ResponseEntity(mensaje, HttpStatus.OK)
    }

    @GetMapping("/{idTienda}", produces = arrayOf("application/json"))
    fun getStoreById(@PathVariable("idTienda") idTienda: Long) : ResponseEntity<Any> {
        try {
            val tienda = service.getStoreById(idTienda)// ?: return ResponseEntity("Registro no encontrado",HttpStatus.NOT_FOUND)
            if (!tienda.isPresent()) return  ResponseEntity("Registro no encontrado",HttpStatus.NOT_FOUND)
            val t = tienda.get()
            return ResponseEntity(t, HttpStatus.OK)
        } catch (ex: NullPointerException) {
            throw ApiRequestException("oops :3")
        }
    }

    @GetMapping("/listar", produces = arrayOf("application/json"))
    fun getVendedores(): ResponseEntity<Any> {
        return ResponseEntity(service.getTiendas(), HttpStatus.OK)
    }

}