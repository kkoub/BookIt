package cz.bookit.backend.domain.model.affiliate

import javax.persistence.*

@Entity
@Table(name = "affiliate_member")
class AffiliateMember(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val affiliateId: Long,
    val name: String,
    val availability: String,
    val description: String,
)