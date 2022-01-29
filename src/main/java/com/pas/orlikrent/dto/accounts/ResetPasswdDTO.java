package com.pas.orlikrent.dto.accounts;

import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswdDTO implements SignableEntity {
    private String id;
    private String login;
    private String oldPasswd;
    private String newPasswd;
    private String token;

    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id+login;
    }

}
