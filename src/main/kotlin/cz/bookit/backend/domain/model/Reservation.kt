package cz.bookit.backend.domain.model

import javax.persistence.*

@Entity
@Table(name = "reservations")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val userId: Long,
    val affiliateId: Long,
    val affiliateMemberId: Long,
    val serviceId: Long?,
    val timeSlotId: Long?,
)
