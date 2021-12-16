package com.pas.orlikrent.dto.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientForRegistrationDTO {

    private String login;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
}
