package com.pas.orlikrent.model;

import com.pas.orlikrent.model.Pitch_Types.Ground_type;
import com.pas.orlikrent.model.Pitch_Types.Sector;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Football_pitch {
    private UUID id;
    private String name;
    private Ground_type grass_type;
    private Double price;
    private Boolean is_goal_nets;
    private Boolean is_lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean is_rented;

    public Football_pitch(String name, Ground_type grass_type, Double price, Boolean is_goal_nets, Boolean is_lights, Sector sector, Integer min_people, Integer max_people) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.grass_type = grass_type;
        this.price = price;
        this.is_goal_nets = is_goal_nets;
        this.is_lights = is_lights;
        this.sector = sector;
        this.min_people = min_people;
        this.max_people = max_people;
        this.is_rented = false;
    }
}
