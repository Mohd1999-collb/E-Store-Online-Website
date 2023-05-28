package com.example.EStore.Exception;

public class EmptyCartException extends Exception{
    public EmptyCartException(String message){
        super(message);
    }
}
