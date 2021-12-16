package com.pas.orlikrent.dto.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerForRegistrationDTO {

    private String login;
    private String email;
    private Double salary;
    private Integer numberOfShifts;
}
