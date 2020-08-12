package br.com.dasa.laboratory_service.laboratory

import br.com.dasa.laboratory_service.common.BadRequestException
import br.com.dasa.laboratory_service.common.generateUUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "laboratories")
class LaboratoryEntity(
        @Id
        @Column(name = "id", nullable = false, length = 32)
        val id: String,

        @Column(name = "name", nullable = false, length = 100)
        val name: String,

        @Column(name = "address", nullable = false, length = 100)
        val address: String,

        @Column(name = "activated", nullable = false)
        val activated: Boolean,

        @Column(name = "version", nullable = false)
        val version: Int) {

    fun update(request: UpdateLaboratoryRequest): LaboratoryEntity {
        if (request.version < version) throw BadRequestException(
                message = "Can't update obsolete request",
                reason = "Your object have obsolete version, refresh your object and try again"
        )
        return LaboratoryEntity(
                id = id,
                name = request.name,
                address = request.address,
                activated = request.activated,
                version = version.plus(1)
        )
    }

    fun deactivate(): LaboratoryEntity {
        return LaboratoryEntity(
                id = id,
                name = name,
                address = address,
                activated = false,
                version = version.plus(1)
        )
    }

    companion object {
        fun makeNew(request: NewLaboratoryRequest): LaboratoryEntity {
            return LaboratoryEntity(
                    id = generateUUID(),
                    name = request.name,
                    address = request.address,
                    activated = request.activated,
                    version = 1
            )
        }
    }
}