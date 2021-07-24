package com.nuestratienda.admin.model

class RespuestaCulqui(
    var isSuccessful: Boolean = false,
    var mensaje: String = "",
    var id: String = ""
){
    override fun toString(): String {
        return "RespuestaCulqui(isSuccessful=$isSuccessful, mensaje='$mensaje', id='$id')"
    }
}