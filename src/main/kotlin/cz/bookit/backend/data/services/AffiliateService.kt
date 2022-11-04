package cz.bookit.backend.data.services

import cz.bookit.backend.data.repositories.affiliate.AffiliateMemberRepository
import cz.bookit.backend.data.repositories.affiliate.AffiliateRepository
import cz.bookit.backend.data.repositories.affiliate.ServiceRepository
import cz.bookit.backend.data.repositories.company.CompanyRepository
import cz.bookit.backend.domain.model.affiliate.Affiliate
import cz.bookit.backend.domain.model.affiliate.AffiliateMember
import cz.bookit.backend.domain.model.affiliate.ServiceModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
data class AffiliateService(
    private val affiliateRepository: AffiliateRepository,
    private val affiliateMemberRepository: AffiliateMemberRepository,
    private val companyRepository: CompanyRepository,
    private val serviceRepository: ServiceRepository,
) {
    fun createAffiliate(affiliate: Affiliate): Affiliate {
        val doesCompanyExist = companyRepository.existsById(affiliate.companyId)
        if (!doesCompanyExist) throw ResponseStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Company with id ${affiliate.companyId} does not exist."
        )

        val doesAlreadyAffiliateExist = affiliateRepository.existsById(affiliate.id)
        if (!doesAlreadyAffiliateExist) return affiliateRepository.save(affiliate)
        else throw ResponseStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Affiliate with id ${affiliate.id} already exists."
        )
    }

    fun createAffiliateMember(affiliateMember: AffiliateMember): AffiliateMember {
        val doesAffiliateExist = affiliateRepository.existsById(affiliateMember.affiliateId)
        if (doesAffiliateExist) return affiliateMemberRepository.save(affiliateMember)
        else throw ResponseStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Affiliate with id ${affiliateMember.affiliateId} does not exist."
        )
    }

    fun getAffiliateServices(affiliateId: Long): List<ServiceModel> =
        serviceRepository.findByAffiliateId(affiliateId)

    fun insertNewAffiliateService(service: ServiceModel): ServiceModel =
        serviceRepository.save(service)
}