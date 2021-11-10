package com.pas.orlikrent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    private UUID id;
    private String login;
    private String password;
    private String email;
    private Boolean active;
    private String role;
    private String first_name;
    private String last_name;

    public Account(String login, String password, String email, String first_name, String last_name, String role) {
        this.id= UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = true;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
