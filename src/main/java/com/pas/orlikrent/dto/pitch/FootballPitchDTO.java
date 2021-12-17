package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  //todo BŁAGAM POPRAW TO DTO XDD
public class FootballPitchDTO extends PitchDTO implements SignableEntity {

    private String id;
    private String name;
    private Double price;
    private Boolean lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean rented = false;

    private GroundType grass_type;
    private Boolean goal_nets;


    //   Do sprawdzenia z extends
    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return id;
    }
}