package cz.bookit.backend.api.interfaces

interface ReservationDao {

    fun getReservation(id: Long)

    fun cancelReservation(id: Long)

    fun getReservations()

    fun createReservation()
}