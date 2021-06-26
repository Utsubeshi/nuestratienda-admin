package com.nuestratienda.admin.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Registro no encontrado")
class TiendaNotFoundException : Exception() {
}