package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Pitch_Repo_Exception;
import com.pas.orlikrent.exceptions.Rental_Repo_Exception;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Pitch_Rental;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Rental_Repository {
    private List<Pitch_Rental> rentals =  Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void initData(){
        //todo init data
    }

    public List<Pitch_Rental> getAllRentals(){
        synchronized (this.rentals){
            return Collections.unmodifiableList(rentals);
        }
    }
    public Pitch_Rental getRental( UUID id) throws Rental_Repo_Exception {
        for( Pitch_Rental rent: rentals){
            if(rent.getId().equals(id)){
                return rent;
            }
        }
        throw new Rental_Repo_Exception("Cannot find rental with given uuid");
    }

    public void addRental(Pitch_Rental rent) throws Rental_Repo_Exception {
        synchronized (this.rentals) {
            for (Pitch_Rental r : rentals) {
                if (rentals.contains(r)) {
                    throw new Rental_Repo_Exception("There is rental already exists");
                }
            }
            rentals.add(rent);
        }
    }
    public void removeRental(Pitch_Rental rent) throws Rental_Repo_Exception {
        synchronized (this.rentals) {
            try {
                rentals.remove(rent);
            } catch (Exception e) {
                throw new Rental_Repo_Exception("Cannot remove given rental ", e);
            }
        }
    }
    public void updateRent(UUID oldRent, Pitch_Rental newRent){
        synchronized (this.rentals) {
            for (int i = 0; i < rentals.size(); i++) {
                if (oldRent.equals(rentals.get(i).getId())) {
                    this.rentals.set(i, newRent);
                }
            }
        }
    }
}
