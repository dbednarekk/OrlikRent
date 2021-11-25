package com.pas.orlikrent.model.Users;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Manager extends Account {

    @JsonbProperty
    private float salary;
    @JsonbProperty
    private int numberOfShifts;

    public Manager(String login, String password, String email, Boolean active, String role, float salary, int numberOfShifts) {
        super(login, password, email, active, role);
        this.salary = salary;
        this.numberOfShifts = numberOfShifts;
    }

  /*  public Manager(UUID id, String login, String password, String email, Boolean active, String role, float salary, int numberOfShifts) {
        super(id, login, password, email, active, role);
        this.salary = salary;
        this.numberOfShifts = numberOfShifts;
    }*/
}

