package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Rental__Exception;
import com.pas.orlikrent.model.PitchRental;
import com.pas.orlikrent.model.Users.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@ApplicationScoped
public class Rental_Repository implements IRentalRepository {
    private final List<PitchRental> rentals = Collections.synchronizedList(new ArrayList<>());


    @PostConstruct
    private void initData() {

    }

    public List<PitchRental> getAll() {
        synchronized (this.rentals) {
            return rentals;
        }
    }

    public PitchRental getByID(String id) throws Rental__Exception {
        synchronized (this.rentals) {
            for (PitchRental rent : rentals) {
                if (rent.getId().equals(id)) {
                    return rent;
                }
            }
            throw new Rental__Exception("Cannot find rental with given id");
        }
    }

    public void add(PitchRental rent) throws Rental__Exception {
        synchronized (this.rentals) {
//            for (PitchRental r : rentals) {
//                if ((r.getPitch().getId().equals(rent.getPitch().getId())) && (r.getActive())) {
//                    throw new Rental__Exception("There is rental already exists");
//                }
//            }
            rent.setActive(true);
            rent.setId(UUID.randomUUID().toString());
            rentals.add(rent);

        }
    }

    public void remove(PitchRental rent) throws Rental__Exception {
        synchronized (this.rentals) {
            if(rent.getActive()){
                throw new Rental__Exception("Cannot remove given rental while it has reservation");
            }
            rentals.remove(rent);
        }
    }

    public void update(String oldRent, PitchRental newRent) {
        synchronized (this.rentals) {

            for (int i = 0; i < rentals.size(); i++) {
                if (oldRent.equals(rentals.get(i).getId())) {
                    this.rentals.set(i, newRent);
                }
            }
        }
    }
    public List<PitchRental> getRentalsForPitch(String id){
        synchronized (this.rentals) {
            List<PitchRental> res = new ArrayList<>();
            for (PitchRental r : rentals
            ) {
                if (r.getPitch().getId().equals(id)) {
                    res.add(r);
                }
            }
            return res;
        }
    }
    public List<PitchRental> getRentalsForClient(String login){
        synchronized (this.rentals) {
            List<PitchRental> res = new ArrayList<>();
            for (PitchRental r : rentals
            ) {
                if (r.getAccount().getLogin().equals(login)) {
                    res.add(r);
                }
            }
            return res;
        }
    }
    public void endReservation(String id){
        synchronized (this.rentals) {
            for (PitchRental r : rentals) {
                if (r.getId().equals(id)) {
                    r.setActive(false);
                }
            }
        }
    }
}
