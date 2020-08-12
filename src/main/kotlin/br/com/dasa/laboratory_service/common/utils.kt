package br.com.dasa.laboratory_service.common

import java.util.UUID

fun generateUUID(): String = UUID.randomUUID().toString().replace("-", "")