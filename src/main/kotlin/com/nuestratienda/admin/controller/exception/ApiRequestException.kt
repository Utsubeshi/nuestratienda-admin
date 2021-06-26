package com.nuestratienda.admin.controller.exception

class ApiRequestException (
    override val message: String,
    override val cause: Throwable
        ) : RuntimeException() {
}