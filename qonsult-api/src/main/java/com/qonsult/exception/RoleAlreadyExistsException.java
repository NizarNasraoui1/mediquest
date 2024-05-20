package com.qonsult.exception;

public class RoleAlreadyExistsException extends Exception{
    public RoleAlreadyExistsException(){
        super("role already exists!");
    }

}
