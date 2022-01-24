package com.pas.orlikrent.dao;

import com.pas.orlikrent.model.PitchRental;

import java.util.List;

public interface IRentalRepository extends IRepository<PitchRental,String> {
     List<PitchRental> getRentalsForPitch(String id);
     void endReservation(String id);
}
