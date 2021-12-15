package com.pas.orlikrent.managers;

import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Pitch_Rental;

public interface IPitchRentalManager extends IManager<Pitch_Rental,String> {
        void createRent(Pitch_Rental rent) throws Base_Exception;
        void endReservation(String id) throws Base_Exception;
}
