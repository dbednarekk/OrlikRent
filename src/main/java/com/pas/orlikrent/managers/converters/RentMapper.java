package com.pas.orlikrent.managers.converters;


import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchRentDTO;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;

import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;
import com.pas.orlikrent.model.Users.Account;

import java.util.ArrayList;
import java.util.List;

public class RentMapper {
    private RentMapper(){
    }

    public static PitchRental rentalFromDTO(PitchRentalDTO rpDTO,Account acc, Pitch pitch){

        return new PitchRental(acc, pitch, rpDTO.getStart_date_rental(), rpDTO.getEnd_date_rental(), rpDTO.getActive());
    }
    public static PitchRental newRentalFromDTO(PitchRentDTO rpDTO, Account acc, Pitch pitch){
            pitch.setRented(true);
        return new PitchRental(acc, pitch, rpDTO.getStart_date_rental(), rpDTO.getEnd_date_rental(), rpDTO.getActive());
    }

    public static PitchRentalDTO rentalToDTO(PitchRental rpM){
        return new PitchRentalDTO(rpM.getId(), rpM.getAccount().getId(), rpM.getPitch().getId(), rpM.getStart_date_rental(), rpM.getEnd_date_rental(), rpM.getActive());
    }
    public static List<PitchRentalDTO> listRentalToDTO(List<PitchRental> rpM){
        List<PitchRentalDTO> res = new ArrayList<>();
        for (PitchRental r:rpM
             ) {
            res.add(rentalToDTO(r));
        }
        return res;
    }

}