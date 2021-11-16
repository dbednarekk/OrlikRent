package com.pas.orlikrent.model;

import com.pas.orlikrent.model.Users.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Pitch_Rental implements Serializable { //todo check if date time is correct
    private UUID id;
    private Account account;
    private Pitch pitch;
    private Date start_date_rental;
    private Date end_date_rental;

   /* public Pitch_Rental(Account account, Pitch pitch, Date start_date_rental, Date end_date_rental) {
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
    }*/
    @JsonbCreator
    public Pitch_Rental(@JsonbProperty("account") Account account,
                        @JsonbProperty("pitch")Pitch pitch,
                        @JsonbProperty("start_date")Date start_date_rental,
                        @JsonbProperty("end_date")Date end_date_rental) {//todo implement validation
        this.account = account;
        this.pitch = pitch;
        this.start_date_rental = start_date_rental;
        this.end_date_rental = end_date_rental;
    }

}
