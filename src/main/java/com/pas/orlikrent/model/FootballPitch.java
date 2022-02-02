package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import lombok.*;

import javax.validation.constraints.NotEmpty;


@Data
public class FootballPitch extends Pitch {
    @NotEmpty
    private GroundType grass_type;
    private Boolean goal_nets;

    public FootballPitch(String name, Double price, Boolean lights, Sector sector, Integer min_people, Integer max_people, Boolean goal_nets, GroundType grass_type){

                  super(name,price,lights,sector,min_people,max_people);
                  this.grass_type = grass_type;
                  this.goal_nets = goal_nets;
                }


}
