package com.pas.orlikrent.dto.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Name;
import com.pas.orlikrent.validators.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements SignableEntity {
    @NotEmpty
    private String id;
    @Login
    private String login;
    @Email
    private String email;

    private Boolean active;
    @Role
    private String role;
    @Name
    private String first_name;
    @Name
    private String last_name;

    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id+login;
    }
}
