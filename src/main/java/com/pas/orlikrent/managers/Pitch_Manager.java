package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.exceptions.Pitch_Manager_Exception;
import com.pas.orlikrent.model.Basketball_pitch;
import com.pas.orlikrent.model.Football_pitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Pitch_Rental;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Client;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Pitch_Manager implements IPitchManager {

    @Inject
    private IRepository<Pitch,String> pitches_repo;
    @Inject IRepository<Pitch_Rental,String> rentals;

    @Override
    public List<Pitch> getAll() {
        return pitches_repo.getAll();
    }

    @Override
    public Pitch getByID(String id) throws Base_Exception {
        return pitches_repo.getByID(id);
    }

    @Override
    public void add(Pitch o) throws Base_Exception {
        pitches_repo.add(o);
    }

    @Override
    public void remove(Pitch o) throws Base_Exception {
        for(Pitch_Rental r : rentals.getAll()){
            if(r.getPitch().getId().equals(o.getId())){
                throw new Pitch_Manager_Exception("Cannot remove pitch while it has reservations");
            }
        }
        pitches_repo.remove(o);
    }

    @Override
    public void update(String id, Pitch o) throws Base_Exception {
        pitches_repo.update(id,o);
    }

    @Override
    public List<Pitch> getAllFootballPitches() {
        List<Pitch> football_pitches = this.getAll().stream().filter(pitch -> pitch instanceof Football_pitch).collect(Collectors.toList());
        return football_pitches;
    }

    @Override
    public List<Pitch> getAllBasketballPitches() {
        List<Pitch> basketball_pitch = this.getAll().stream().filter(pitch -> pitch instanceof Basketball_pitch).collect(Collectors.toList());
        return basketball_pitch;
    }
}
