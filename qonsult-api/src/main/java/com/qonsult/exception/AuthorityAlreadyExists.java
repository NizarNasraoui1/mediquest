package com.qonsult.exception;

public class AuthorityAlreadyExists extends Exception{
    public AuthorityAlreadyExists(){
        super("authority already exists");
    }
}
