package com.pas.orlikrent.exceptions;

public class Rental_Repo_Exception extends Exception{
    public Rental_Repo_Exception(String message){super(message);}
    public Rental_Repo_Exception(String message, Throwable cause) {super(message,cause);}
}