package br.com.dasa.laboratory_service.laboratory

import br.com.dasa.laboratory_service.common.created
import br.com.dasa.laboratory_service.common.noContent
import br.com.dasa.laboratory_service.common.success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/v1/laboratories")
class LaboratoryResource(val laboratoryService: LaboratoryService) {

    @PostMapping
    fun createLaboratory(@RequestBody request: NewLaboratoryRequest): ResponseEntity<LaboratoryResponse> {
        val newLaboratoryResponse: LaboratoryResponse = laboratoryService.createNewLaboratory(request)
        return created(newLaboratoryResponse)
    }

    @GetMapping
    fun fetchLaboratories(@RequestParam("activated") activated: Boolean?): ResponseEntity<List<LaboratoryResponse>?> {
        val laboratoriesResponse: List<LaboratoryResponse>? = laboratoryService.findLaboratories(activated)
        return success(laboratoriesResponse)
    }

    @PutMapping("/{id}")
    fun updateLaboratory(
            @PathVariable("id") id: String,
            @RequestBody request: UpdateLaboratoryRequest): ResponseEntity<Void> {
        laboratoryService.updateLaboratory(id, request)
        return noContent()
    }

    @DeleteMapping("/{id}")
    fun deactivateLaboratory(@PathVariable("id") id: String): ResponseEntity<Void> {
        laboratoryService.deactivateLaboratory(id)
        return noContent()
    }

}