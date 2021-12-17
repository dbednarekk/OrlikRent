package com.pas.orlikrent.managers;

import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.PitchRental;

public interface IPitchRentalManager extends IManager<PitchRentalDTO,String> {
        void createRent(PitchRentalDTO rent) throws Base_Exception;
        void endReservation(String id) throws Base_Exception;
}
