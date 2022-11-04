package cz.bookit.backend.data.services

import cz.bookit.backend.data.repositories.affiliate.AffiliateMemberRepository
import cz.bookit.backend.data.repositories.ReservationRepository
import cz.bookit.backend.data.repositories.affiliate.AffiliateRepository
import cz.bookit.backend.domain.model.Reservation
import cz.bookit.backend.domain.model.affiliate.Affiliate
import cz.bookit.backend.domain.model.affiliate.AffiliateMember
import cz.bookit.backend.utils.takeIfFound
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
        val affiliateMember = affiliateMemberRepository.findById(reservation.affiliateMemberId)
                .takeIfFound(AffiliateMember::class.java.name)
        val affiliate = affiliateRepository.findById(reservation.affiliateId)
            .takeIfFound(Affiliate::class.java.name)

        val isMemberPartOfAffiliate = affiliate.id == affiliateMember.affiliateId

        if (isMemberPartOfAffiliate) {
            return reservationRepository.save(reservation)
        } else {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Affiliate member with id ${reservation.affiliateMemberId} not found" +
                        " for affiliate with id ${affiliate.id}"
            )
        }
    }

    fun cancelReservation(reservationId: Long) {
        val properReservation = reservationRepository.findById(reservationId)
            .takeIfFound(Reservation::class.java.name)
        reservationRepository.deleteById(properReservation.id!!)
    }
}