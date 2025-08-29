package com.bookstore.meccrascunhos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException implements Serializable {

    public RequiredObjectIsNullException() {
        super("Não é permitida a persistência de objetos nulos.");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }
}
