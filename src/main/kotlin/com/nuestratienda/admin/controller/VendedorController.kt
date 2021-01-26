package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.repository.VendedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("vendedores")
class VendedorController @Autowired constructor(
    val repository: VendedorRepository ) {

    @PostMapping("/registro")
    fun saveNewUser(@RequestBody vendedor: Vendedor) : ResponseEntity<Any> {
        repository.save(vendedor)
        return ResponseEntity.created(URI("/registro/" + vendedor.id)).body("")
    }
}