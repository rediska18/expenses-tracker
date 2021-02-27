package com.application.expensestracker.exception;

public class RequestValidationException extends ApplicationException{
    public RequestValidationException() {
        super("An unknown error has occured. Please contact your application administrator.");
    }
}
