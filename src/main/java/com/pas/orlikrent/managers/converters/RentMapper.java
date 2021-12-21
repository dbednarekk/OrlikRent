package com.pas.orlikrent.managers.converters;


import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;

import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.PitchRental;

public class RentMapper {
    private RentMapper(){
    }

    public static PitchRental rentalFromDTO(PitchRentalDTO rpDTO){
        return new PitchRental(rpDTO.getAccount(), rpDTO.getPitch(), rpDTO.getStart_date_rental(), rpDTO.getEnd_date_rental(), rpDTO.getActive());
    }

    public static PitchRentalDTO rentalToDTO(PitchRental rpM){
        return new PitchRentalDTO(rpM.getId(), rpM.getAccount(), rpM.getPitch(), rpM.getStart_date_rental(), rpM.getEnd_date_rental(), rpM.getActive());
    }
}