package org.trahim.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bad file name.")
@SuppressWarnings("serial")
public class BadFileNameException extends Exception {
    public BadFileNameException() {

    }

    public BadFileNameException(String message) {
        super(message);
    }

}
