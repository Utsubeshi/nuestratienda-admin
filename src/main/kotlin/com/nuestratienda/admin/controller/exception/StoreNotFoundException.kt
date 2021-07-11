package com.nuestratienda.admin.controller.exception

import org.springframework.http.HttpStatus

class StoreNotFoundException (): ApiException(
    message = "Store not found",
    httpStatus = HttpStatus.NOT_FOUND
) {
}