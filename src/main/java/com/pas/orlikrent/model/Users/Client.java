package com.pas.orlikrent.model.Users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public class Client extends Account{

    @JsonbProperty
    private String first_name;
    @JsonbProperty
    private String last_name;

    public Client(String login, String password, String email, Boolean active, String role, String first_name, String last_name) {
        super(login, password, email, active, role);
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Client(String id, String login, String password, String email, Boolean active, String role, String first_name, String last_name) {
        super(id, login, password, email, active, role);
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
