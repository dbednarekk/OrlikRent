package com.pas.orlikrent.model.Users;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manager extends Account {

    private Double salary;
    private Integer numberOfShifts;

    public Manager(String login, String password, String email, Boolean active, String role, Double salary, Integer numberOfShifts) {
        super(login, password, email, active, role);
        this.salary = salary;
        this.numberOfShifts = numberOfShifts;
    }

}

