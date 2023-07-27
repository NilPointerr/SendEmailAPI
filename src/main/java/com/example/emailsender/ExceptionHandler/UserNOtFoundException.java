package com.example.emailsender.ExceptionHandler;

public class UserNOtFoundException extends Exception{
   public UserNOtFoundException(){}

    public UserNOtFoundException(String msg){
        super(msg);
    }
}
