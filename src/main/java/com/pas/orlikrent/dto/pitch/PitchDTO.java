package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitchDTO implements SignableEntity {

    private String id;
    private String name;
    private Double price;
    private Boolean lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean rented = false;

    @JsonIgnore
    @Override
    public String getSignablePayload() {
        return id;
    }
}