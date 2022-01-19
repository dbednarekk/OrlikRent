package com.pas.orlikrent.dao;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchDTO;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;

import java.util.List;

public interface IPitchRepository extends IRepository<Pitch, String>{

    List<FootballPitch> getAllFootball();

    List<BasketballPitch> getAllBasketball();

    List<Pitch> getAllTypedPitch();
    void setRented( String id, boolean t);
}
