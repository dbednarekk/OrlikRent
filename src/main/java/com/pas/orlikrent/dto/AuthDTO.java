package com.pas.orlikrent.dto;

import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthDTO {
    @Login
    private String login;
    @Password
    private String password;

    public Credential toCredential(){
        return new UsernamePasswordCredential(login, password);
    }
}
