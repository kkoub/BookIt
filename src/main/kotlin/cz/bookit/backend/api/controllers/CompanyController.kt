package cz.bookit.backend.api.controllers

import cz.bookit.backend.api.headers.UserIdHeader
import cz.bookit.backend.api.versions.V1
import cz.bookit.backend.data.services.CompanyService
import cz.bookit.backend.domain.model.UserUuid
import cz.bookit.backend.domain.model.affiliate.Affiliate
import cz.bookit.backend.domain.model.company.Company
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*

@V1
data class CompanyController(
    private val companyService: CompanyService
) {

    @GetMapping("/company/{companyId}/affiliates")
    fun getCompanyAffiliates(
        @RequestHeader(UserIdHeader) userId : UserUuid,
        @PathVariable companyId: Long
    ): ResponseEntity<List<Affiliate>> {
        return ResponseEntity(
            companyService.getAllCompanyAffiliates(companyId),
            HttpStatus.OK
        )
    }
    @PostMapping("/company")
    fun createCompany(
        @RequestBody company: Company
    ): ResponseEntity<Company> {
        val persistedCompany = companyService.createCompany(company)
        if (ObjectUtils.isEmpty(persistedCompany)) {
            return ResponseEntity<Company>(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(null, HttpStatus.CREATED)
    }
}