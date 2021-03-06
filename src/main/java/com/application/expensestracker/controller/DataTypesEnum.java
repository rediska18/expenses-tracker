package com.application.expensestracker.controller;

import org.springframework.util.MimeType;

import java.nio.charset.Charset;

/**
 @author Ярослав
 @date 05.03.2021
 @version 1.0
 */

public enum DataTypesEnum {
    // TODO імплемеентувати UTF-16 / UTF-32
    APPLICATION_JSON_UTF8(new MimeType("application", "json", Charset.forName("UTF-8"))),
    TEXT_PLAIN_UTF8(new MimeType("text", "plain", Charset.forName("UTF-8")));

    private MimeType mimeType;

    DataTypesEnum(MimeType mimeType){
        this.mimeType = mimeType;
    }

    public static MimeType getMimeTypeNameByType(DataTypesEnum expectedDataType){
        for (DataTypesEnum typesEnum: DataTypesEnum.values()){
            if (typesEnum.equals(expectedDataType)){
                return typesEnum.getMimeType();
            }
        }
        return null;
    }

    public MimeType getMimeType(){
        return mimeType;
    }

    public static void main(String[] args) {
        System.out.println(DataTypesEnum.APPLICATION_JSON_UTF8.mimeType);
    }
}
