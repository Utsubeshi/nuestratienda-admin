package com.nuestratienda.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@RestController
class SaludoController @Autowired constructor(
    val repositoty: SaludosRepositoty) {

    val saludos = listOf<Saludo>(Saludo(1,"Hola"), Saludo(2, "Buenas noches"))

    @GetMapping("/")
    fun HolaMundo(): String = "Hola!!"

    @GetMapping("/saludos")
    fun saludos() : Iterable<Saludo> {
        return saludos
    }

    @GetMapping("/listar" )
    fun listar() : ResponseEntity<Any> {
        return ResponseEntity.created(URI("/api/")).body(repositoty.findAll())
    }

    @PostMapping("/guardar")
    fun guardar(@RequestBody saludo: Saludo) : ResponseEntity<Any> {
        if (repositoty.existsById(saludo.id))
            return ResponseEntity(HttpStatus.CONFLICT)
        repositoty.save(saludo)
        return ResponseEntity.created( URI("/api/" + saludo.id)).body("")
    }


}

@Entity
data class Saludo (
    @Id
    @GeneratedValue
    var id: Long = 0,
    @Column
    var mensaje: String) {

}
interface SaludosRepositoty : CrudRepository<Saludo, Long> {
}