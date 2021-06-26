package com.nuestratienda.admin.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZoneId
import java.time.ZonedDateTime

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(value = [ApiRequestException::class])
    fun handler(exception: ApiRequestException) : ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val apiException = ApiException(
            exception.message,
            exception,
            status,
            ZonedDateTime.now(ZoneId.of("GMT-5")))
        return ResponseEntity(apiException, status)
    }
}