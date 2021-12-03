package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Ground_type;
import com.pas.orlikrent.model.enums.Sector;
import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;


@Data
public class Basketball_pitch  extends Pitch {

    private Double numberOfBaskets;


    @JsonbCreator
    public Basketball_pitch( @JsonbProperty("id") String id,
                           @JsonbProperty("name") String name,
                           @JsonbProperty("price") Double price,
                           @JsonbProperty("lights") Boolean lights,
                           @JsonbProperty("sector") Sector sector,
                           @JsonbProperty("min_people") Integer min_people,
                           @JsonbProperty("max_people") Integer max_people,
                           @JsonbProperty("number_of_baskets") Double number_of_baskets
                          ){ //todo implement validation

        super(id,name,price,lights,sector,min_people,max_people);
        this.numberOfBaskets = number_of_baskets;
    }
}

