package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.converters.RentMapper;
import com.pas.orlikrent.model.PitchRental;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class PitchRentalManager implements IPitchRentalManager {

    @Inject
    private IRepository<PitchRental, String> pitch_rental_repo;

    @Override
    public List<PitchRentalDTO> getAll() {
        List<PitchRentalDTO> res = new ArrayList<>();
        for (PitchRental ac : this.pitch_rental_repo.getAll()) {
            res.add(RentMapper.rentalToDTO(ac));
        }
        return res;
    }

    @Override
    public PitchRentalDTO getByID(String id) throws Base_Exception {
        return RentMapper.rentalToDTO(this.pitch_rental_repo.getByID(id));
    }

    //todo add w sumie niepotrzebny jeśli niżej jest create
    @Override
    public void add(PitchRentalDTO o) throws Base_Exception {
        this.pitch_rental_repo.add(RentMapper.rentalFromDTO(o));
    }

    //todo na pewno trzeba dodać jakąś walidacje czy można usunąć np czy nie jest aktywne wypożyczenie
    @Override
    public void remove(PitchRentalDTO o) throws Base_Exception {
        this.pitch_rental_repo.remove(RentMapper.rentalFromDTO(o));
    }

    //todo też walidacja, że update np jeżeli dostępny jest kolejny termin
    @Override
    public void update(String id, PitchRentalDTO o) throws Base_Exception {
        this.pitch_rental_repo.update(id, RentMapper.rentalFromDTO(o));
    }

    //todo też walidacja, np żeby nie tworzyć dwoch rentów do tego samego boiska
    @Override
    public void createRent(PitchRentalDTO rent) throws Base_Exception {
        this.pitch_rental_repo.add(RentMapper.rentalFromDTO(rent));
    }

    @Override
    public void endReservation(String id) throws Base_Exception {
        PitchRental rent = this.pitch_rental_repo.getByID(id);
        if (rent == null) {
            throw new Base_Exception("reservation does not exists"); //todo change Base_Exception to RentManager_Exception
        }
        rent.setActive(false);
    }
}
