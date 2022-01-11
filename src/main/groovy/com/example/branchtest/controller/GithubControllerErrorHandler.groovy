package com.example.branchtest.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

import com.example.branchtest.service.github.NotFoundException
import com.example.branchtest.service.github.OverQuotaException

@ControllerAdvice
class GithubControllerErrorHandler {

    // might be helpful to distinguish between not found for users and repos...

    @ExceptionHandler(NotFoundException)
    @ResponseBody
    ResponseEntity<Map> notFound(NotFoundException exception) {
        return new ResponseEntity<>([message: exception.message], HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(OverQuotaException)
    @ResponseBody
    ResponseEntity<Map> overQuota(OverQuotaException exception) {
        return new ResponseEntity<>([message: exception.message], HttpStatus.FORBIDDEN)
    }

}
