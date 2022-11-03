package cz.bookit.backend.api.controllers

import cz.bookit.backend.api.versions.V1
import org.springframework.web.bind.annotation.GetMapping

@V1
class ReservationController {

    @GetMapping("/")
    fun getReservations(): String {
        return "SMRDIS KURVA"
    }
}