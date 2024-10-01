package com.boubyan.boubyantask.exception;

import com.boubyan.boubyantask.exception.model.HttpError;
import org.springframework.http.HttpStatus;

public interface HttpException {
    HttpError getHttpError();

    HttpStatus getHttpStatus();

}
