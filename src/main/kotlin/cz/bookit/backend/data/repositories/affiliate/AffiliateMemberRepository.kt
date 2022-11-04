package cz.bookit.backend.data.repositories.affiliate

import cz.bookit.backend.domain.model.affiliate.AffiliateMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AffiliateMemberRepository : JpaRepository<AffiliateMember, Long>