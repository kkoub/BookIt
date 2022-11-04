package cz.bookit.backend.api.controllers

import cz.bookit.backend.api.versions.V1
import cz.bookit.backend.data.services.CompanyService
import cz.bookit.backend.domain.model.company.Company
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@V1
data class CompanyController(
    private val companyService: CompanyService
) {

    @PutMapping("/company")
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