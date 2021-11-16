package com.pas.orlikrent.model.Users;


import com.pas.orlikrent.model.jsonbCustomAdapter.CustomAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public  abstract class Account implements Serializable {
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

    public Account(String login, String password, String email, Boolean active, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }

    public Account(UUID id, String login, String password, String email, Boolean active, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id) && login.equals(account.login) && password.equals(account.password) && email.equals(account.email) && active.equals(account.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }


    //    @JsonbProperty

//    private String first_name;
//    @JsonbProperty
//    private String last_name;

//
//    @JsonbCreator
//    public Account( @JsonbProperty("login") String login,
//                    @JsonbProperty("password") String password,
//                    @JsonbProperty("email") String email,
//                    @JsonbProperty("first_name") String first_name,
//                    @JsonbProperty("last_name") String last_name,
//                    @JsonbProperty("role") String role) {
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.active = true;
//        this.role = role;
//        this.first_name = first_name;
//        this.last_name = last_name;
//    }
}
