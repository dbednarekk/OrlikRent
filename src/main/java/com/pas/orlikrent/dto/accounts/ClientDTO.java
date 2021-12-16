package com.pas.orlikrent.dto.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements SignableEntity {
    private String id;
    private String login;
    private String email;
    private Boolean active;
    private String role;
    private String first_name;
    private String last_name;

    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return id;
    }
}
