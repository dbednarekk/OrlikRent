package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Ground_type;
import com.pas.orlikrent.model.enums.Sector;
import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;


@Data
public class Basketball_pitch  extends Pitch {

    private Double number_of_baskets;


    @JsonbCreator
    public Basketball_pitch( @JsonbProperty("uuid") UUID uuid,
                           @JsonbProperty("name") String name,
                           @JsonbProperty("price") Double price,
                           @JsonbProperty("lights") Boolean lights,
                           @JsonbProperty("sector") Sector sector,
                           @JsonbProperty("min_people") Integer min_people,
                           @JsonbProperty("max_people") Integer max_people,
                           @JsonbProperty("number_of_baskets") Double number_of_baskets,
                           @JsonbProperty("grass_type") Ground_type grass_type){ //todo implement validation

        super(uuid,name,price,lights,sector,min_people,max_people);
        this.number_of_baskets = number_of_baskets;
    }
}

