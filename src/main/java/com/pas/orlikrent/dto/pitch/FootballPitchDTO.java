package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FootballPitchDTO implements SignableEntity {
    @NotEmpty
    private String id;
    @Name
    private String name;
    @PositiveOrZero
    private Double price;
    private Boolean lights;
    private Sector sector;
    @PositiveOrZero
    private Integer min_people;
    @PositiveOrZero
    private Integer max_people;
    private Boolean rented;
    @NotEmpty
    private GroundType grass_type;
    private Boolean goal_nets;


    //   Do sprawdzenia z extends
    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return id;
    }
}