package com.pas.orlikrent.dto.accounts;

import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswdDTO implements SignableEntity {
    @NotEmpty
    private String id;
    @Login
    private String login;
    @Password
    private String oldPasswd;
    @Password
    private String newPasswd;
    @NotEmpty
    private String token;

    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id;
    }

}
