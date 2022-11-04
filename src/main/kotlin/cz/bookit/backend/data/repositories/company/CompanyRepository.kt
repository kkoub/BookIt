package cz.bookit.backend.data.repositories.company

import cz.bookit.backend.domain.model.company.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long>