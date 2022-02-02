package com.pas.orlikrent.dto.accounts;

import com.pas.orlikrent.validators.Login;
import com.pas.orlikrent.validators.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerForRegistrationDTO {
    @Login
    private String login;
    @Password
    private String password;
    @Email
    private String email;
    @DecimalMin("0.00")
    @DecimalMax("20000.00")
    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    private Integer numberOfShifts;
}
