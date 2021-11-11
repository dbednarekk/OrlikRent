package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Ground_type;
import com.pas.orlikrent.model.enums.Sector;
import lombok.*;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import java.util.UUID;


@Data
public class Football_pitch  extends Pitch {

    private Ground_type grass_type;
    private Boolean goal_nets;


    @JsonbCreator
    public Football_pitch( @JsonbProperty("uuid") UUID uuid,
                           @JsonbProperty("name") String name,
                           @JsonbProperty("price") Double price,
                           @JsonbProperty("lights") Boolean lights,
                           @JsonbProperty("sector") Sector sector,
                           @JsonbProperty("min_people") Integer min_people,
                           @JsonbProperty("max_people") Integer max_people,
                           @JsonbProperty("goal_nets") Boolean goal_nets,
                           @JsonbProperty("grass_type") Ground_type grass_type){ //todo implement validation

                  super(uuid,name,price,lights,sector,min_people,max_people);
                  this.grass_type = grass_type;
                  this.goal_nets = goal_nets;
                }
}
