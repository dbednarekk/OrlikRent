package com.pas.orlikrent.managers;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;

import java.util.List;

public interface IPitchManager{
    List<PitchDTO> getAll();

    PitchDTO getByID(String id) throws Base_Exception;

    FootballPitchDTO getFByID(String id) throws Base_Exception;

    BasketballPitchDTO getBByID(String id) throws Base_Exception;
    
    List<FootballPitchDTO> getAllFootballPitches() throws Base_Exception;

    List<BasketballPitchDTO> getAllBasketballPitches() throws Base_Exception;

    boolean isPitchFootball(String id) throws Base_Exception;

    boolean isPitchBasketball(String id) throws Base_Exception;

    void addFootballPitch(FootballPitchDTO f) throws Base_Exception;

    void addBasketballPitch(BasketballPitchDTO b) throws Base_Exception;

    void remove(String id) throws Base_Exception;

    void updateFootballPitch(String id, FootballPitchDTO o) throws Base_Exception;

    void updateBasketballPitch(String id, BasketballPitchDTO o) throws Base_Exception;

}
