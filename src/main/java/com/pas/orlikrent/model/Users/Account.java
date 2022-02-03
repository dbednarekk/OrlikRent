package com.pas.orlikrent.model.Users;


import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Password;
import com.pas.orlikrent.validators.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@NoArgsConstructor
public abstract class Account implements Serializable {

    private String id;
    @Login
    private String login;
    @Password
    private String password;
    @Email
    private String email;
    private Boolean active;
    @Role
    private String role;

    public Account(String login, String password, String email, Boolean active, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }
    public Account(String login,  String email, Boolean active, String role) {
        this.login = login;
        this.email = email;
        this.active = active;
        this.role = role;
    }

}
