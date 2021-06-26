package com.nuestratienda.admin.controller.exception


import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

class ApiException (
    val message: String?,
    val throwable: Throwable,
    val httpStatus: HttpStatus,
    val timeStamp: ZonedDateTime
        ) {

}