package cz.bookit.backend.data.repositories.affiliate

import cz.bookit.backend.domain.model.affiliate.Affiliate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AffiliateRepository : JpaRepository<Affiliate, Long> {

    @Query("select * from affiliates af where af.company_id = :companyId", nativeQuery = true)
    fun findByCompany_Id(@Param("companyId") companyId: Long): List<Affiliate>
}