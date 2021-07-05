package com.nuestratienda.admin.controller.exception

import org.springframework.http.HttpStatus

class UserNotFoundException (): ApiException(
    message = "User not found",
    httpStatus = HttpStatus.NOT_FOUND
) {
}