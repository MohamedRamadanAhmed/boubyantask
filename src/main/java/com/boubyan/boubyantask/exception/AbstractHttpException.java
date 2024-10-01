package com.boubyan.boubyantask.exception;

import com.boubyan.boubyantask.exception.model.HttpError;
import org.springframework.http.HttpStatus;

public abstract class AbstractHttpException extends RuntimeException implements HttpException {

    protected final HttpStatus httpStatus;

    protected final HttpError httpError;

    protected AbstractHttpException(HttpError httpError, HttpStatus httpStatus) {

        super(httpError.getMessage());

        this.httpError = httpError;
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {

        return httpStatus;
    }

    @Override
    public HttpError getHttpError() {

        return httpError;
    }


}
