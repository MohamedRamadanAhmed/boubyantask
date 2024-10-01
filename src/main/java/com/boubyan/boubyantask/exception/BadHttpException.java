package com.boubyan.boubyantask.exception;


import com.boubyan.boubyantask.exception.model.HttpError;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadHttpException extends AbstractHttpException {
    public BadHttpException(HttpError httpError) {
        super(httpError, BAD_REQUEST);
    }
}
