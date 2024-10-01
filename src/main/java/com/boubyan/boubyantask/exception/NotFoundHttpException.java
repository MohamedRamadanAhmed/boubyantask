package com.boubyan.boubyantask.exception;


import com.boubyan.boubyantask.exception.model.HttpError;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundHttpException extends AbstractHttpException {
    public NotFoundHttpException(HttpError httpError) {
        super(httpError, NOT_FOUND);
    }
}
