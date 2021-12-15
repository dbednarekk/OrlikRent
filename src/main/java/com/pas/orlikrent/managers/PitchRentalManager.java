package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Pitch_Rental;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
@Named
@ApplicationScoped
public class PitchRentalManager implements IPitchRentalManager{

    @Inject
    private IRepository<Pitch_Rental,String> pitch_rental_repo;

    @Override
    public List<Pitch_Rental> getAll() {
        return this.pitch_rental_repo.getAll();
    }

    @Override
    public Pitch_Rental getByID(String id) throws Base_Exception {
        return this.pitch_rental_repo.getByID(id);
    }

    @Override
    public void add(Pitch_Rental o) throws Base_Exception {
        this.pitch_rental_repo.add(o);
    }

    @Override
    public void remove(Pitch_Rental o) throws Base_Exception {
        this.pitch_rental_repo.remove(o);
    }

    @Override
    public void update(String id, Pitch_Rental o) throws Base_Exception {
        this.pitch_rental_repo.update(id,o);
    }

    @Override
    public void createRent(Pitch_Rental rent) throws Base_Exception {
        this.pitch_rental_repo.add(rent);
    }

    @Override
    public void endReservation(String id) throws Base_Exception {
        Pitch_Rental rent = this.pitch_rental_repo.getByID(id);
        if(rent==null){
            throw new Base_Exception("reservation does not exists"); //todo change Base_Exception to RentManager_Exception
        }
        rent.setActive(false);
    }
}
