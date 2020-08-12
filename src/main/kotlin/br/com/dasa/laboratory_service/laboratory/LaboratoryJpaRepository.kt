package br.com.dasa.laboratory_service.laboratory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface LaboratoryJpaRepository : JpaRepository<LaboratoryEntity, String> {

    @Query(nativeQuery = true,
            value = """select lab.id, lab.name, lab.address, lab.activated, lab.version
                       from laboratories lab where lab.id = :id""")
    fun fetchById(@Param("id") id: String): LaboratoryEntity?

    @Query(nativeQuery = true,
            value = """select lab.id, lab.name, lab.address, lab.activated, lab.version 
                       from laboratories lab where lab.activated = :status""")
    fun fetchAllByStatus(@Param("status") status: Boolean): List<LaboratoryEntity>?
}