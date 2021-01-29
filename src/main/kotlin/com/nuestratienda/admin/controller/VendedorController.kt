package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.repository.VendedorRepository
import com.nuestratienda.admin.service.VendedorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
//@CrossOrigin(origins = arrayOf("*"), methods= arrayOf(RequestMethod.GET, RequestMethod.POST))
@RequestMapping("/api/vendedor/registro")
class VendedorController (
    val service: VendedorService) {

    @PostMapping
    fun saveNewUser(@RequestBody vendedor: Vendedor): ResponseEntity<String> {
        return ResponseEntity<String>(service.saveNewUser(vendedor), HttpStatus.OK)
    }

}