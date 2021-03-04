package com.application.expensestracker.controller;

import org.springframework.util.MimeType;
import java.nio.charset.Charset;

public class DataTypes {
    //public static final MimeType APPLICATION_JSON_UTF8 = new MimeType("application", "json", Charset.forName("UTF-8"));
    //public static final MimeType TEXT_PLAIN_UTF8 = new MimeType("text", "plain", Charset.forName("UTF-8"));

    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=\"utf-8\"";
    public static final String TEXT_PLAIN_UTF8 = "text/plain;charset=\"utf-8\"";;
}
