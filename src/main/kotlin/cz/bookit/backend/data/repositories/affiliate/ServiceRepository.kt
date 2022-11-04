package cz.bookit.backend.data.repositories.affiliate

import cz.bookit.backend.domain.model.affiliate.ServiceModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository : JpaRepository<ServiceModel, Long> {

    @Query("select sc.* from services sc where sc.affiliate_id = :affiliateId", nativeQuery = true)
    fun findByAffiliateId(@Param("affiliateId") affiliateId: Long): List<ServiceModel>
}