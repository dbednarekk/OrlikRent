package com.pas.orlikrent.dto.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ManagerDTO implements SignableEntity {
    @NotEmpty
    private String id;
    @Login
    private String login;
    @Email
    private String email;

    private Boolean active;
    @Role
    private String role;

    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    private Integer numberOfShifts;


    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id;
    }
}
