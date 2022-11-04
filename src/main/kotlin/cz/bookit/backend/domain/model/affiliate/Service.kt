package cz.bookit.backend.domain.model.affiliate

import javax.persistence.*

@Entity
@Table(name = "services")
data class Service(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val affiliateId: Long,
    val affiliatePersonId: Long,
    val serviceName: String,
    val serviceDuration: Long,
    val servicePrice: Double,
)