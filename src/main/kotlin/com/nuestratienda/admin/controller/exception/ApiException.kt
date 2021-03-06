package com.nuestratienda.admin.controller.exception


import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

open class ApiException(
    override val message: String = "",
    val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException () {

}