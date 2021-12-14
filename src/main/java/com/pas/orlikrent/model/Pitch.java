package com.pas.orlikrent.model;

import com.pas.orlikrent.model.enums.Sector;
import com.pas.orlikrent.security.SignableEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class Pitch implements Serializable, SignableEntity {

    @JsonbProperty
    private String id;
    @JsonbProperty
    private String name;
    @JsonbProperty
    private Double price;
    @JsonbProperty
    private Boolean lights;
    @JsonbProperty
    private Sector sector;
    @JsonbProperty
    private Integer min_people;
    @JsonbProperty
    private Integer max_people;
    @JsonbProperty
    private Boolean rented = false;
    @JsonbTransient
    @Override
    public String getSignablePayload() {
        return this.id;
    }

/*    public Pitch(String name, Double price, Boolean lights, Sector sector, Integer min_people, Integer max_people) {
        this.name = name;
        this.price = price;
        this.lights = lights;
        this.sector = sector;
        this.min_people = min_people;
        this.max_people = max_people;
    }*/

    @JsonbCreator
    public Pitch( @JsonbProperty("id") String id,
                  @JsonbProperty("name") String name,
                  @JsonbProperty("price") Double price,
                  @JsonbProperty("lights") Boolean lights,
                  @JsonbProperty("sector") Sector sector,
                  @JsonbProperty("min_people") Integer min_people,
                  @JsonbProperty("max_people") Integer max_people){ //todo implement validation
        this.name = name;
        this.price = price;
        this.lights = lights;
        this.sector = sector;
        this.min_people = min_people;
        this.max_people = max_people;
    }
}
