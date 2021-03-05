package com.application.expensestracker.exception;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
public class RequestValidationException extends ApplicationException{
    public RequestValidationException() {
        super("An unknown error has occured. Please contact your application administrator.");
    }
}
