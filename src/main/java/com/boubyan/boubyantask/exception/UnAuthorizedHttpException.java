package com.boubyan.boubyantask.exception;


import com.boubyan.boubyantask.exception.model.HttpError;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnAuthorizedHttpException extends AbstractHttpException {
    public UnAuthorizedHttpException(HttpError httpError) {
        super(httpError, UNAUTHORIZED);
    }
}
