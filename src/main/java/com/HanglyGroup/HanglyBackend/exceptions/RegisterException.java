package com.HanglyGroup.HanglyBackend.exceptions;

public class RegisterException extends RuntimeException{

    public RegisterException(){
        super("Missing fields to register.");
    }

    public RegisterException(String message){super(message);}



}