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
public class Basketball_pitch_Rent {
    private UUID id;
    private Basketball_pitch b_pitch;
    private Account account;
    private Date start_rental_date;
    private Date end_rental_date;

    public Basketball_pitch_Rent(Basketball_pitch b_pitch, Account account, Date start_rental_date, Date end_rental_date) {
        this.id = UUID.randomUUID();
        this.b_pitch = b_pitch;
        this.account = account;
        this.start_rental_date = start_rental_date;
        this.end_rental_date = end_rental_date;
    }
}

