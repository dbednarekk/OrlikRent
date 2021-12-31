package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitchRentalDTO implements SignableEntity {

    private String id;
    private Account account;
    private Pitch pitch;
    private LocalDateTime start_date_rental;
    private LocalDateTime end_date_rental;
    private Boolean active;


    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id;
    }
}