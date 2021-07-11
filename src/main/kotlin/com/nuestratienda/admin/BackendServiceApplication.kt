package com.nuestratienda.admin


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
open class BackendServiceApplication

fun main(args: Array<String>) {
    runApplication<BackendServiceApplication>(*args)
}



