package br.com.dasa.laboratory_service.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> created(t: T): ResponseEntity<T> = ResponseEntity.status(HttpStatus.CREATED).body(t)
fun <T> success(t: T?): ResponseEntity<T?> = ResponseEntity.status(HttpStatus.OK).body(t)
fun noContent(): ResponseEntity<Void> = ResponseEntity.status(HttpStatus.NO_CONTENT).build()

data class ErrorResponse(
        val code: Int,
        val message: String,
        val reason: String?
)