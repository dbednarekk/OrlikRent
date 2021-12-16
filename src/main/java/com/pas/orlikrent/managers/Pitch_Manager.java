package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.exceptions.Pitch_Manager_Exception;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class Pitch_Manager implements IPitchManager {

    @Inject
    private IRepository<Pitch,String> pitches_repo;
    @Inject IRepository<PitchRental,String> rentals;

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
        for(PitchRental r : rentals.getAll()){
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
        List<Pitch> football_pitches = this.getAll().stream().filter(pitch -> pitch instanceof FootballPitch).collect(Collectors.toList());
        return football_pitches;
    }

    @Override
    public List<Pitch> getAllBasketballPitches() {
        List<Pitch> basketball_pitch = this.getAll().stream().filter(pitch -> pitch instanceof BasketballPitch).collect(Collectors.toList());
        return basketball_pitch;
    }
}
