package com.nuestratienda.admin.controller

import com.nuestratienda.admin.model.PusherRequest
import com.pusher.rest.Pusher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pusher")
class PusherController {

    @PostMapping("/auth", produces = arrayOf("application/json"))
    fun update(@RequestBody req: PusherRequest): ResponseEntity<Any> {
        val pusher = Pusher("1158630", "7357430a418c50d7acec", "0804eeecd910f7bb9c9d")

        val socketId = req.socket_id
        val channel = req.channel_name
        val auth = pusher.authenticate(socketId, channel)
        return ResponseEntity(auth, HttpStatus.OK)
    }
}
