package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import com.pas.orlikrent.validators.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class Pitch implements Serializable {

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
    private Boolean rented = false;


    public Pitch(String name, Double price, Boolean lights, Sector sector, Integer min_people, Integer max_people) {
        this.name = name;
        this.price = price;
        this.lights = lights;
        this.sector = sector;
        this.min_people = min_people;
        this.max_people = max_people;
    }


}
