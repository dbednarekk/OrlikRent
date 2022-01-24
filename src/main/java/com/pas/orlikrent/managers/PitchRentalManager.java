package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.dao.IPitchRepository;
import com.pas.orlikrent.dao.IRentalRepository;
import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.dto.pitch.PitchRentDTO;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.exceptions.Pitch_Manager_Exception;
import com.pas.orlikrent.exceptions.Rental__Exception;
import com.pas.orlikrent.managers.converters.RentMapper;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class PitchRentalManager implements IPitchRentalManager {

    @Inject
    private IRentalRepository pitch_rental_repo;
    @Inject
    private IAccount_Repo account_repo;
    @Inject
    IPitchRepository pitchRepository;
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


    @Override
    public void add(PitchRentalDTO o) throws Base_Exception {
    }

    @Override
    public void remove(PitchRentalDTO o) throws Base_Exception {
        if(o.getActive()){
            throw new Rental__Exception("Can not delete this this reservation while is active");
        }
        this.pitch_rental_repo.remove(RentMapper.rentalFromDTO(o,account_repo.getByID(o.getAccountID()),pitchRepository.getByID(o.getPitchID())));
    }


    @Override
    public void update(String id, PitchRentalDTO o) throws Base_Exception {

        this.pitch_rental_repo.update(id, RentMapper.rentalFromDTO(o,account_repo.getByID(o.getAccountID()),pitchRepository.getByID(o.getPitchID())));
    }
    @Override
    public void createRent(PitchRentDTO rent) throws Base_Exception {
        if(pitchRepository.getByID(rent.getPitchID()).getRented()){
            throw new Rental__Exception("Can not reserve this pitch while it has reservations at the same time");
        }
        for (PitchRental r : pitch_rental_repo.getAll()) {
            if (r.getPitch().getId().equals(rent.getPitchID()) && r.getActive()){
                throw new Rental__Exception("Can not reserve this pitch while it has reservations at the same time");
            }
        }
        if(rent.getEnd_date_rental().isBefore(rent.getStart_date_rental()) ||
                rent.getStart_date_rental().isBefore(LocalDateTime.now().minusMinutes(1)) ||
                rent.getStart_date_rental().isAfter(rent.getEnd_date_rental()))
        {
            throw new Rental__Exception("Reservation dates are not valid");
        }
        pitchRepository.setRented(rent.getPitchID(),true);
        this.pitch_rental_repo.add(RentMapper.newRentalFromDTO(rent,account_repo.getByID(rent.getAccountID()),pitchRepository.getByID(rent.getPitchID())));


    }

    @Override
    public void endReservation(String id) throws Base_Exception {
        PitchRental rent = this.pitch_rental_repo.getByID(id);
        if (rent == null) {
            throw new Rental__Exception("reservation does not exists");
        }
        pitch_rental_repo.endReservation(id);
        pitchRepository.setRented(rent.getPitch().getId(),false);
    }

    @Override
    public List<PitchRentalDTO> rentsForPitch(String id){
            return RentMapper.listRentalToDTO(this.pitch_rental_repo.getRentalsForPitch(id));
    }
}
