package br.com.dasa.laboratory_service.laboratory

import br.com.dasa.laboratory_service.common.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LaboratoryService(private val laboratoryJpaRepository: LaboratoryJpaRepository) {

    @Transactional
    fun createNewLaboratory(laboratoryRequest: NewLaboratoryRequest): LaboratoryResponse {
        val newLaboratoryEntity = LaboratoryEntity.makeNew(laboratoryRequest)
        laboratoryJpaRepository.save(newLaboratoryEntity)
        return LaboratoryResponse(newLaboratoryEntity)
    }

    @Transactional(readOnly = true)
    fun findLaboratories(activated: Boolean?): List<LaboratoryResponse>? {
        val laboratoryEntities: List<LaboratoryEntity>? = laboratoryJpaRepository.fetchAllByStatus(activated ?: true)
        return laboratoryEntities?.let { laboratories ->
            laboratories.map { laboratory -> LaboratoryResponse(laboratory) }
        }
    }

    @Transactional(readOnly = true)
    fun findLaboratory(id: String): LaboratoryEntity = laboratoryJpaRepository.fetchById(id)
            ?: throw NotFoundException("Laboratory not found for id $id")

    @Transactional
    fun updateLaboratory(id: String, laboratoryRequest: UpdateLaboratoryRequest) {
        val laboratoryEntity: LaboratoryEntity = findLaboratory(id)
        val updatedLaboratoryEntity: LaboratoryEntity = laboratoryEntity.update(laboratoryRequest)
        laboratoryJpaRepository.save(updatedLaboratoryEntity)
    }

    @Transactional
    fun deactivateLaboratory(id: String) {
        val laboratoryEntity: LaboratoryEntity = findLaboratory(id)
        val deactivatedLaboratoryEntity: LaboratoryEntity = laboratoryEntity.deactivate()
        laboratoryJpaRepository.save(deactivatedLaboratoryEntity)
    }
}