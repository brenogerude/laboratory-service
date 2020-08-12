package br.com.dasa.laboratory_service

import br.com.dasa.laboratory_service.common.BadRequestException
import br.com.dasa.laboratory_service.common.NotFoundException
import br.com.dasa.laboratory_service.common.generateUUID
import br.com.dasa.laboratory_service.laboratory.LaboratoryEntity
import br.com.dasa.laboratory_service.laboratory.LaboratoryJpaRepository
import br.com.dasa.laboratory_service.laboratory.LaboratoryService
import br.com.dasa.laboratory_service.laboratory.UpdateLaboratoryRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LaboratoryServiceTests {

    @Mock
    private lateinit var jpaRepository: LaboratoryJpaRepository

    @InjectMocks
    private lateinit var service: LaboratoryService
    
    @Test
    fun `trying to update invalid laboratory gives exception`() {
        val id = generateUUID()
        val updateLaboratoryRequest = UpdateLaboratoryRequest(
                name = "Test Laboratory",
                address = "Test Address",
                activated = true,
                version = 1
        )
        Mockito.`when`(jpaRepository.fetchById(id)).thenReturn(null)
        Assertions.assertThrows(NotFoundException::class.java) {
            service.updateLaboratory(id, updateLaboratoryRequest)
        }
    }

    @Test
    fun `trying to update obsolete laboratory gives exception`() {
        val id = generateUUID()
        val updateLaboratoryRequest = UpdateLaboratoryRequest(
                name = "Test Laboratory",
                address = "Test Address",
                activated = true,
                version = 1
        )
        val foundedLaboratoryException = LaboratoryEntity(id = id, name = "Test Laboratory", address = "Test Address", activated = true, version = 2)
        Mockito.`when`(jpaRepository.fetchById(id)).thenReturn(foundedLaboratoryException)
        Assertions.assertThrows(BadRequestException::class.java) {
            service.updateLaboratory(id, updateLaboratoryRequest)
        }
    }
}
