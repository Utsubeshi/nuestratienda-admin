package com.nuestratienda.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
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