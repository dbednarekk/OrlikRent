package com.pas.orlikrent.validators;

public class ValidationRegex {
    public static final String LOGIN = "^\\w{3,30}$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,}$";
    public static final String NAME = "^[\\p{L}' -]+\\.?$";
    public static final String ROLE ="^CLIENT$|^ADMINISTRATOR$|^MANAGER|^USER$|";
}
