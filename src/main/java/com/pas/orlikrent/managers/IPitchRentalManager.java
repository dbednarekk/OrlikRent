package com.pas.orlikrent.managers;

import com.pas.orlikrent.dto.pitch.PitchRentDTO;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.PitchRental;

import java.util.List;

public interface IPitchRentalManager extends IManager<PitchRentalDTO,String> {
        void createRent(PitchRentDTO rent) throws Base_Exception;
        void endReservation(String id) throws Base_Exception;
        List<PitchRentalDTO> rentsForPitch(String id);
}
