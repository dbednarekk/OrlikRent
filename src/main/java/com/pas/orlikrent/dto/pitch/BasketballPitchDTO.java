package com.pas.orlikrent.dto.pitch;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class BasketballPitchDTO implements SignableEntity {
    @NotEmpty
    private String id;
    @Name
    private String name;
    @DecimalMin("0.00")
    @DecimalMax("20000.00")
    @PositiveOrZero
    private Double price;
    private Boolean lights;
    @NotEmpty
    private Sector sector;
    @PositiveOrZero
    private Integer min_people;
    @PositiveOrZero
    private Integer max_people;
    private Boolean rented;
    @PositiveOrZero
    private Integer numberOfBaskets;


//   Do sprawdzenia z extends
@JsonbTransient
    @Override
    public String getSignablePayload() {
//        return id + String.valueOf(numberOfBaskets);
    return id;
    }
}