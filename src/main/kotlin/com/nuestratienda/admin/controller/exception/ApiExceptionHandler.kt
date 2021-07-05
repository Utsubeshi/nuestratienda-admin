package com.nuestratienda.admin.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.ZoneId
import java.time.ZonedDateTime

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handler(exception: ApiException) : ResponseEntity<Any> {
        //val status = exception.httpStatus
        val mensaje: MutableMap<String, String> = HashMap()
        mensaje.put("message", exception.message)
        return ResponseEntity(mensaje, exception.httpStatus)
    }
}