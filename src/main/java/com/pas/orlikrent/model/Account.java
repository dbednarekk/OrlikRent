package com.pas.orlikrent.model;


import com.pas.orlikrent.model.jsonbCustomAdapter.CustomAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Account implements Serializable {
    @JsonbProperty
    private UUID id;
    @JsonbProperty
    private String login;
    @JsonbTypeAdapter(CustomAdapter.class)
    private String password;
    @JsonbProperty
    private String email;
    @JsonbProperty
    private Boolean active;
    @JsonbProperty
    private String role;
    @JsonbProperty
    private String first_name;
    @JsonbProperty
    private String last_name;


    @JsonbCreator
    public Account( @JsonbProperty("login") String login,
                    @JsonbProperty("password") String password,
                    @JsonbProperty("email") String email,
                    @JsonbProperty("first_name") String first_name,
                    @JsonbProperty("last_name") String last_name,
                    @JsonbProperty("role") String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = true;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
