package com.pas.orlikrent.model.Users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;


@Data
@NoArgsConstructor
public class Admin extends Account{

      /*  @JsonbProperty
        private Boolean Active;  // moze coś w zamian*/

        public Admin(String login, String password, String email, Boolean active, String role) {
                super(login, password, email, active, role);

        }

    /*    public Admin(UUID id, String login, String password, String email, Boolean active, String role, Boolean Active) {
                super(id, login, password, email, active, role);
                this.Active = Active;
        }*/
}
