package com.pas.orlikrent.exceptions;

public class Base_Exception extends Exception {
    public Base_Exception(String message) {
        super(message);
    }
    public Base_Exception(String message, Throwable cause) {super(message,cause);}
}
