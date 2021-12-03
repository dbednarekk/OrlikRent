package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.exceptions.Pitch_Manager_Exception;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Pitch_Rental;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class Pitch_Manager implements IManager<Pitch, String> {

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
}
