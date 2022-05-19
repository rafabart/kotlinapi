package com.kotlinapi.exception

import java.util.*

data class ExceptionResponse(
    val timestamp: Date,
    val message: String?,
    val details: String
)