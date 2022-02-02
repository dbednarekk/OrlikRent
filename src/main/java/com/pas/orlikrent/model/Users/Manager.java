package com.pas.orlikrent.model.Users;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class Manager extends Account {
    @DecimalMin("0.00")
    @DecimalMax("20000.00")
    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    private Integer numberOfShifts;

    public Manager(String login, String password, String email, Boolean active, String role, Double salary, Integer numberOfShifts) {
        super(login, password, email, active, role);
        this.salary = salary;
        this.numberOfShifts = numberOfShifts;
    }

}

