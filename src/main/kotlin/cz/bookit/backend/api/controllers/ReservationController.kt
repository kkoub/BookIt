package cz.bookit.backend.api.controllers

import cz.bookit.backend.api.headers.UserIdHeader
import cz.bookit.backend.api.versions.V1
import cz.bookit.backend.data.services.ReservationService
import cz.bookit.backend.domain.model.Reservation
import cz.bookit.backend.domain.model.UserUuid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@V1
data class ReservationController(
    private val reservationService: ReservationService
) {

    @GetMapping("/reservations")
    fun getReservations(@RequestHeader(UserIdHeader) userId : UserUuid): ResponseEntity<List<Reservation>> {
        return ResponseEntity(reservationService.getReservations(), HttpStatus.OK)
    }

    @PutMapping("/reservation/create")
    fun createReservation(
        @RequestHeader(UserIdHeader) userId : UserUuid,
        @RequestBody reservation: Reservation,
    ): ResponseEntity<Reservation> {
        val persistedReservation = reservationService.createReservation(reservation)
        if (ObjectUtils.isEmpty(persistedReservation)) {
            return ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(persistedReservation, HttpStatus.OK)
    }
}