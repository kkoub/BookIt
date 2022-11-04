package cz.bookit.backend.domain.model.affiliate

import javax.persistence.*

@Entity
@Table(name = "affiliates")
data class Affiliate(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "company_id")
    val companyId: Long,
    val name: String,
    val address: String,
    val telephone: String,
    val email: String,
    val opening: String,
    val closing: String
)