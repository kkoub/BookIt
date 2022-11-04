package cz.bookit.backend.data.services

import cz.bookit.backend.data.repositories.affiliate.AffiliateMemberRepository
import cz.bookit.backend.data.repositories.ReservationRepository
import cz.bookit.backend.data.repositories.affiliate.AffiliateRepository
import cz.bookit.backend.domain.model.Reservation
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
data class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val affiliateRepository: AffiliateRepository,
    private val affiliateMemberRepository: AffiliateMemberRepository,
) {

    fun getReservations() : List<Reservation> = reservationRepository.findAll()

    fun createReservation(reservation: Reservation) : Reservation {
        val doesAffiliateMemberExists = affiliateMemberRepository.existsById(reservation.affiliateMemberId)
        val doesAffiliateExists = affiliateRepository.existsById(reservation.affiliateId)

        if (doesAffiliateExists && doesAffiliateMemberExists) {
            return reservationRepository.save(reservation)
        } else if (!doesAffiliateExists) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Affiliate with id ${reservation.affiliateId} not found."
            )
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Affiliate member with id ${reservation.affiliateMemberId} not found."
            )
        }
    }
}