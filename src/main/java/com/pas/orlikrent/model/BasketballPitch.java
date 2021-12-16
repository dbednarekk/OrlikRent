package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Sector;
import lombok.Data;


@Data
public class BasketballPitch extends Pitch {

    private Double numberOfBaskets;


    public BasketballPitch(String name, Double price, Boolean lights, Sector sector, Integer min_people, Integer max_people, Double number_of_baskets
    ) {

        super(name, price, lights, sector, min_people, max_people);
        this.numberOfBaskets = number_of_baskets;
    }
}

