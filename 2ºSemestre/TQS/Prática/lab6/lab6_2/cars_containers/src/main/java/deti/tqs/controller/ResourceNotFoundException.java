package deti.tqs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
class ResourceNotFoundException extends Exception {
    ResourceNotFoundException(String message) {
        super(message);
    }
}
