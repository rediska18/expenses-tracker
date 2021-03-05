package com.application.expensestracker.exception;

import java.lang.annotation.*;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApplicationExceptionHandler {
}
