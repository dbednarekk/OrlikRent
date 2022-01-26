package com.pas.orlikrent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthDTO {
    private String login;
    private String password;

    public Credential toCredential(){
        return new UsernamePasswordCredential(login, password);
    }
}
