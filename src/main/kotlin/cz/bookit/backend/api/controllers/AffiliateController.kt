package cz.bookit.backend.api.controllers

import cz.bookit.backend.api.headers.UserIdHeader
import cz.bookit.backend.api.versions.V1
import cz.bookit.backend.data.services.AffiliateService
import cz.bookit.backend.data.services.CompanyService
import cz.bookit.backend.domain.model.UserUuid
import cz.bookit.backend.domain.model.affiliate.Affiliate
import cz.bookit.backend.domain.model.affiliate.AffiliateMember
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@V1
data class AffiliateController(
    private val affiliateService: AffiliateService,
    private val companyService: CompanyService,
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

    @PutMapping("/affiliate")
    fun createAffiliate(
        @RequestHeader(UserIdHeader) userId : UserUuid,
        @RequestBody affiliate: Affiliate
    ): ResponseEntity<Affiliate> {
        val persistedAffiliate = affiliateService.createAffiliate(affiliate)
        if (ObjectUtils.isEmpty(persistedAffiliate)) {
            return ResponseEntity<Affiliate>(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(persistedAffiliate, HttpStatus.OK)
    }

    @PutMapping("/affiliate/member")
    fun createAffiliateMember(
        @RequestHeader(UserIdHeader) userId : UserUuid,
        @RequestBody affiliateMember: AffiliateMember
    ): ResponseEntity<AffiliateMember> {
        val persistedMember = affiliateService.createAffiliateMember(affiliateMember)
        if (ObjectUtils.isEmpty(persistedMember)) {
            return ResponseEntity<AffiliateMember>(HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(null, HttpStatus.CREATED)
    }
}