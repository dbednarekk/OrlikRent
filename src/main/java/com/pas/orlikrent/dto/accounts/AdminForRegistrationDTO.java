package com.pas.orlikrent.dto.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminForRegistrationDTO {

    private String login;
    private String password;
    private String email;
}
