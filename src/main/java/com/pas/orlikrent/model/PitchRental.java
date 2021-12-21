package com.pas.orlikrent.model;

import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.security.SignableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PitchRental implements Serializable {
    private String id;
    private Account account;
    private Pitch pitch;
    private LocalDateTime start_date_rental;
    private LocalDateTime end_date_rental;
    private Boolean active = false;

    public PitchRental(Account account, Pitch pitch, LocalDateTime start_date_rental, LocalDateTime end_date_rental, Boolean active) {
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
        this.active = active;
    }

    public PitchRental(Account account, Pitch pitch, LocalDateTime start_date_rental, LocalDateTime end_date_rental) {
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
    }
}
