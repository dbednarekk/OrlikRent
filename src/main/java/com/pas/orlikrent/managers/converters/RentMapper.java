package com.pas.orlikrent.managers.converters;


import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchRentDTO;
import com.pas.orlikrent.dto.pitch.PitchRentDTO2;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;

import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;
import com.pas.orlikrent.model.Users.Account;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RentMapper {
    private RentMapper(){
    }

    public static PitchRental rentalFromDTO(PitchRentalDTO rpDTO,Account acc, Pitch pitch){

        return new PitchRental(acc, pitch, rpDTO.getStart_date_rental(), rpDTO.getEnd_date_rental(), rpDTO.getActive());
    }
    public static PitchRental rentalFromDTOWid(String id, PitchRentalDTO rpDTO,Account acc, Pitch pitch){
        PitchRental rRent = new PitchRental();
        rRent.setId(id);
        rRent.setAccount(acc);
        rRent.setPitch(pitch);
        rRent.setStart_date_rental(rpDTO.getStart_date_rental());
        rRent.setEnd_date_rental(rpDTO.getEnd_date_rental());
        rRent.setActive(rpDTO.getActive());
        return rRent;
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

    public static PitchRentDTO rentalDTO2ToDTO(PitchRentDTO2 prD){
        return new PitchRentDTO(prD.getAccountID(), prD.getPitchID(), LocalDateTime.ofInstant(Instant.parse(prD.getStart_date_rental()), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(Instant.parse(prD.getEnd_date_rental()), ZoneId.systemDefault()), prD.getActive());
    }
}