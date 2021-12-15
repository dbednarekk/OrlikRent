package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Rental__Exception;
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
public class Rental_Repository implements IRepository<Pitch_Rental,String>{
    private List<Pitch_Rental> rentals =  Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void initData(){
        //todo init data
    }

    public List<Pitch_Rental> getAll(){
        synchronized (this.rentals){
            return Collections.unmodifiableList(rentals);
        }
    }
    public Pitch_Rental getByID( String id) throws Rental__Exception {
        for( Pitch_Rental rent: rentals){
            if(rent.getId().equals(id)){
                return rent;
            }
        }
        throw new Rental__Exception("Cannot find rental with given id");
    }

    public void add(Pitch_Rental rent) throws Rental__Exception {
        synchronized (this.rentals) {
            for (Pitch_Rental r : rentals) {
                if (rentals.contains(r)) {
                    throw new Rental__Exception("There is rental already exists");
                }
            }
            rent.setActive(true);
            rentals.add(rent);
        }
    }
    public void remove(Pitch_Rental rent) throws Rental__Exception {
        synchronized (this.rentals) {
            try {
                rentals.remove(rent);
            } catch (Exception e) {
                throw new Rental__Exception("Cannot remove given rental ");
            }
        }
    }
    public void update(String oldRent, Pitch_Rental newRent){
        synchronized (this.rentals) {
            for (int i = 0; i < rentals.size(); i++) {
                if (oldRent.equals(rentals.get(i).getId())) {
                    this.rentals.set(i, newRent);
                }
            }
        }
    }
}
