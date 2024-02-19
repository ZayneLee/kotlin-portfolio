package com.zayne.portfolio.admin.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T>(status: HttpStatus) : ResponseEntity<T>(status) {
    companion object {
        fun successCreate(): ResponseEntity<Any> {
            return ResponseEntity.ok("Data saved")
        }
        fun successUpdate(): ResponseEntity<Any> {
            return ResponseEntity.ok("Data updated")
        }
        fun successDelete(): ResponseEntity<Any> {
            return ResponseEntity.ok("Data deleted")
        }
    }
}