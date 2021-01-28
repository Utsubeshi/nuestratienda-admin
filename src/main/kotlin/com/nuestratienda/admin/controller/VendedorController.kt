package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.Vendedor
import com.nuestratienda.admin.repository.VendedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = arrayOf("*"), methods= arrayOf(RequestMethod.GET, RequestMethod.POST))
@RequestMapping("/api")
class VendedorController @Autowired constructor(
    val repository: VendedorRepository ) {

    @PostMapping("/registro")
    fun saveNewUser(@RequestBody vendedor: Vendedor) : ResponseEntity<Any> {
        repository.save(vendedor)
        return ResponseEntity.created(URI("/registro/" + vendedor.id)).body("")
    }

    @GetMapping("/listar",  produces = ["application/json"])
    fun getAllUsers(): Iterable<Vendedor> = repository.findAll()

}