package br.com.dasa.laboratory_service.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(
                        code = HttpStatus.NOT_FOUND.value(),
                        message = exception.message!!,
                        reason = null
                ))
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse(
                        code = HttpStatus.BAD_REQUEST.value(),
                        message = exception.message!!,
                        reason = exception.reason
                ))
    }

}