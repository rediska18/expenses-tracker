package com.application.expensestracker.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.annotation.Annotation;

public class ApplicationException extends RuntimeException implements ExceptionHandler {

    public ApplicationException(String exceptionMessage){
        super(exceptionMessage);
    }

    @Override
    public Class<? extends Throwable>[] value() {
        Class<ApplicationException>[] applicationExceptions = new Class[]{RequestValidationException.class, UknownException.class};
        return applicationExceptions;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ApplicationExceptionHandler.class;
    }
}
