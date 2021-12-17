package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor  //todo BŁAGAM POPRAW TO DTO XDD
public class PitchRentalDTO implements SignableEntity {

    private String id;
    private Account account;
    private Pitch pitch;
    private LocalDateTime start_date_rental;
    private LocalDateTime end_date_rental;
    private Boolean active = false;


    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return id;
    }
}