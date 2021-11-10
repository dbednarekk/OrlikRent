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
public class Basketball_pitch {
    private UUID id;
    private String name;
    private Double price;
    private Boolean is_lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean is_rented;

    public Basketball_pitch(String name, Double price, Boolean is_lights, Sector sector, Integer min_people, Integer max_people) {
        this.id= UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.is_lights = is_lights;
        this.sector = sector;
        this.min_people = min_people;
        this.max_people = max_people;
        this.is_rented=false;
    }
}
