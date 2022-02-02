package com.pas.orlikrent.dto.accounts;

import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Name;
import com.pas.orlikrent.validators.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientForRegistrationDTO {
    @Login
    private String login;
    @Password
    private String password;
    @Email
    private String email;
    @Name
    private String first_name;
    @Name
    private String last_name;
}
