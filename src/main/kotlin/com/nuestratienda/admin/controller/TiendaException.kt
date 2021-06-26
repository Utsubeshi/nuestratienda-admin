package com.nuestratienda.admin.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Problema con el request")
class TiendaException(errorMessage: String?) : Exception(errorMessage) {
    companion object {
        private const val serialVersionUID = 1L
    }
}