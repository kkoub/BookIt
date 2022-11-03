package cz.bookit.backend.domain.model

data class Reservation(
    val id: Long,
    val reservationEntity: ReservationEntity,

    val timeSlot: TimeSlot,
) {

    data class ReservationEntity(
        val company: String,
        val affiliate: Affiliate
    )
}

