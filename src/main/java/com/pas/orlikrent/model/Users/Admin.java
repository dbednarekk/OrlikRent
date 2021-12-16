package com.pas.orlikrent.model.Users;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Admin extends Account {

    public Admin(String login, String password, String email, Boolean active, String role) {
        super(login, password, email, active, role);

    }


}
