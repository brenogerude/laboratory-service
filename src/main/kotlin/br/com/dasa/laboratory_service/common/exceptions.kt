package br.com.dasa.laboratory_service.common

class NotFoundException(message: String) : RuntimeException(message)

class BadRequestException(message: String, val reason:String) : RuntimeException(message)