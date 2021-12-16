package com.pas.orlikrent.model.Users;


import com.pas.orlikrent.security.SignableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public abstract class Account implements Serializable, SignableEntity {

    private String id;
    private String login;
    private String password;
    private String email;
    private Boolean active;
    private String role;

    public Account(String login, String password, String email, Boolean active, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }

}
