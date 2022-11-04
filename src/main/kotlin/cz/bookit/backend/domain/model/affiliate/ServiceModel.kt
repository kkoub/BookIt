package cz.bookit.backend.domain.model.affiliate

import javax.persistence.*

@Entity
@Table(name = "services")
data class ServiceModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val affiliateId: Long,
    val affiliatePersonId: Long,
    val serviceName: String,
    val serviceDurationInMinutes: Long,
    val servicePrice: Double,
)