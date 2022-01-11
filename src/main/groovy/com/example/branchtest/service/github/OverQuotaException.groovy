package com.example.branchtest.service.github

class OverQuotaException extends RuntimeException {

    OverQuotaException(String message, Throwable cause) {
        super(message, cause)
    }

}
