package cz.bookit.backend.domain.model

import javax.persistence.*

@Entity
@Table(name = "timeslots")
data class TimeSlot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val start: Long,
    val end: Long,
)