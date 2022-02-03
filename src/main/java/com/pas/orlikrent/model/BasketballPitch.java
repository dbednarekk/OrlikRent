package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Sector;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;


@Data
@NoArgsConstructor
public class BasketballPitch extends Pitch {
    @PositiveOrZero
    private Integer numberOfBaskets;


    public BasketballPitch(String name, Double price, Boolean lights, Sector sector, Integer min_people, Integer max_people, Integer numberOfBaskets
    ) {

        super(name, price, lights, sector, min_people, max_people);
        this.numberOfBaskets = numberOfBaskets;
    }

}

