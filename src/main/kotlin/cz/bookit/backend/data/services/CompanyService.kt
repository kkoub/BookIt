package cz.bookit.backend.data.services

import cz.bookit.backend.data.repositories.affiliate.AffiliateRepository
import cz.bookit.backend.data.repositories.company.CompanyRepository
import cz.bookit.backend.domain.model.affiliate.Affiliate
import cz.bookit.backend.domain.model.company.Company
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
data class CompanyService(
    private val companyRepository: CompanyRepository,
    private val affiliateRepository: AffiliateRepository,
) {

    fun getAllCompanyAffiliates(companyId: Long): List<Affiliate> =
        affiliateRepository.findByCompany_Id(companyId)

    fun createCompany(company: Company): Company {
        val doesCompanyAlreadyExist = companyRepository.existsById(company.id)
        if (doesCompanyAlreadyExist) throw ResponseStatusException(
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Company with id ${company.id} already exists."
        ) else {
            return companyRepository.save(company)
        }
    }
}