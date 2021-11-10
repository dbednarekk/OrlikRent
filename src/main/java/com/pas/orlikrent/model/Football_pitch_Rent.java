package com.pas.orlikrent.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
//todo check if the data for reservations are valid
public class Football_pitch_Rent {
    private UUID id;
    private Football_pitch f_pitch;
    private Account account;
    private Date start_rental_date;
    private Date end_rental_date;

    public Football_pitch_Rent(Football_pitch f_pitch, Account account, Date start_rental_date, Date end_rental_date) {
        this.id = UUID.randomUUID();
        this.f_pitch = f_pitch;
        this.account = account;
        this.start_rental_date = start_rental_date;
        this.end_rental_date = end_rental_date;
    }
}
