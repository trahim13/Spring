package org.trahim.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by root on 18.07.2017.
 */
@ControllerAdvice
public class ClobalExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger(ClobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured.")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.error("IOException handler executed");
    }


}