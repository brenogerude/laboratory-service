package br.com.dasa.laboratory_service.laboratory

import com.fasterxml.jackson.annotation.JsonIgnore

data class NewLaboratoryRequest(
        val name: String,
        val address: String,
        val activated: Boolean
)

data class UpdateLaboratoryRequest(
        val name: String,
        val address: String,
        val activated: Boolean,
        val version: Int
)

data class LaboratoryResponse(@JsonIgnore val entity: LaboratoryEntity) {
    val id: String = entity.id
    val name: String = entity.name
    val address: String = entity.address
    val activated: Boolean = entity.activated
    val version: Int = entity.version
}