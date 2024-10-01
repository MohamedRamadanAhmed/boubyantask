package com.boubyan.boubyantask.exception.handler;

import com.boubyan.boubyantask.exception.BadHttpException;
import com.boubyan.boubyantask.exception.NotFoundHttpException;
import com.boubyan.boubyantask.exception.UnAuthorizedHttpException;
import com.boubyan.boubyantask.exception.model.HttpError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HttpExceptionHandler {

    @ExceptionHandler(UnAuthorizedHttpException.class)
    public ResponseEntity<HttpError> handleUnauthorizedHttpException(UnAuthorizedHttpException ex) {
        log.error("com/boubyan/boubyantask/exception/handler/HttpExceptionHandler.handleUnauthorizedHttpException():", ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getHttpError());
    }

    @ExceptionHandler(NotFoundHttpException.class)
    public ResponseEntity<HttpError> handleNotFoundHttpException(NotFoundHttpException ex) {
        log.error("com/boubyan/boubyantask/exception/handler/HttpExceptionHandler.handleNotFoundHttpException():", ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getHttpError());
    }

    @ExceptionHandler(BadHttpException.class)
    public ResponseEntity<HttpError> handleBadHttpException(BadHttpException ex) {
        log.error("com/boubyan/boubyantask/exception/handler/HttpExceptionHandler.handleBadHttpException():", ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getHttpError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpError> handleGenericException(Exception ex) {
        log.error("com/boubyan/boubyantask/exception/handler/HttpExceptionHandler.handleGenericException():", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpError.builder().code("Generic").message(ex.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpError.builder().code("BAD_REQUEST").message("argument not valid.").message(ex.getMessage()).build());
    }
}


