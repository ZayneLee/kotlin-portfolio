package com.zayne.portfolio.admin.exception

import org.springframework.http.HttpStatus

abstract class AdminException(
    httpStatus: HttpStatus,
    message: String
) : RuntimeException(message) {
    val httpStatus: HttpStatus = httpStatus
}