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
public class Pitch_Rental implements Serializable, SignableEntity { //todo check if date time is correct
    private String id;
    private Account account;
    private Pitch pitch;
    private LocalDateTime start_date_rental;
    private LocalDateTime end_date_rental;
    private Boolean active = false;
    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return this.id;
    }

   /* public Pitch_Rental(Account account, Pitch pitch, Date start_date_rental, Date end_date_rental) {
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
    }*/
    @JsonbCreator
    public Pitch_Rental(@JsonbProperty("account") Account account,
                        @JsonbProperty("pitch")Pitch pitch,
                        @JsonbProperty("start_date")LocalDateTime start_date_rental,
                        @JsonbProperty("end_date")LocalDateTime end_date_rental) {//todo implement validation
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
    }

}
