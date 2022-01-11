package com.example.branchtest.service.github

class NotFoundException extends RuntimeException {

    NotFoundException(String message, Throwable cause) {
        super(message, cause)
    }

}
