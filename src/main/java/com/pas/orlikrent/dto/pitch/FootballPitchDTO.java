package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballPitchDTO implements SignableEntity {

    private String id;
    private String name;
    private Double price;
    private Boolean lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean rented;

    private GroundType grass_type;
    private Boolean goal_nets;


    //   Do sprawdzenia z extends
    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id;
    }
}