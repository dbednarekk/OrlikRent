package com.pas.orlikrent.dto.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ManagerDTO implements SignableEntity {
    private String id;
    private String login;
    private String email;
    private Boolean active;
    private String role;
    private Double salary;
    private Integer numberOfShifts;


    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return id;
    }
}
