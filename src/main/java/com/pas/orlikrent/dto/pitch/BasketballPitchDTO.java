package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  //todo BŁAGAM POPRAW TO DTO XDD
public class BasketballPitchDTO extends PitchDTO implements SignableEntity {

    private String id;
    private String name;
    private Double price;
    private Boolean lights;
    private Sector sector;
    private Integer min_people;
    private Integer max_people;
    private Boolean rented = false;
    private Double numberOfBaskets;


//   Do sprawdzenia z extends
    @JsonIgnore
    @Override
    public String getSignablePayload() {
//        return id + String.valueOf(numberOfBaskets);
    return id;
    }
}