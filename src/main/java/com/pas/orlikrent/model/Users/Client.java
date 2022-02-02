package com.pas.orlikrent.model.Users;

import com.pas.orlikrent.validators.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client extends Account {
    @Name
    private String first_name;
    @Name
    private String last_name;

    public Client(String login, String password, String email, Boolean active, String role, String first_name, String last_name) {
        super(login, password, email, active, role);
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
