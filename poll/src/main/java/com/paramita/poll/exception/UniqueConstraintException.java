package com.paramita.poll.exception;

public class UniqueConstraintException extends RuntimeException{
    public UniqueConstraintException(String message){
        super(message);
    }
}
