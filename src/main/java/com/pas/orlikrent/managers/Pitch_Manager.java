package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IPitchRepository;
import com.pas.orlikrent.dao.IRentalRepository;
import com.pas.orlikrent.dao.IRepository;
import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.exceptions.Pitch_Manager_Exception;
import com.pas.orlikrent.managers.converters.PitchMapper;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped //todo check scope
@NoArgsConstructor
public class Pitch_Manager implements IPitchManager {

    @Inject
    private IPitchRepository pitches_repo;
    @Inject
    IRentalRepository rentals;

    @Override
    public List<PitchDTO> getAll() {
        List<PitchDTO> res = new ArrayList<>();
        for (Pitch ac : this.pitches_repo.getAll()) {
            res.add(PitchMapper.pitchToDTO(ac));
        }
        return res;
    }

    @Override
    public PitchDTO getByID(String id) throws Base_Exception {
        return PitchMapper.pitchToDTO(pitches_repo.getByID(id));
    }

    @Override
    public boolean isPitchFootball(String id) throws Base_Exception {
        Pitch old_pitch = pitches_repo.getByID(id);
        if (old_pitch instanceof FootballPitch) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean isPitchBasketball(String id) throws Base_Exception {
        Pitch old_pitch = pitches_repo.getByID(id);
        if (old_pitch instanceof BasketballPitch) {
            return true;
        } else {
            return false;
        }    }

    @Override
    public void addFootballPitch(FootballPitchDTO f) throws Base_Exception {
        pitches_repo.add(PitchMapper.footballPitchFromDTO(f));
    }

    @Override
    public void addBasketballPitch(BasketballPitchDTO b) throws Base_Exception {
        pitches_repo.add(PitchMapper.basketballPitchFromDTO(b));
    }

    //todo check if the IF statment is correct  chyba tak
    @Override
    public void remove(String id) throws Base_Exception {
        for (PitchRental r : rentals.getAll()) {
            if (r.getPitch().getId().equals(id) && r.getActive()) {
                throw new Pitch_Manager_Exception("Cannot remove pitch while it has reservations");
            }
        }
        Pitch pitch = pitches_repo.getByID(id);
        pitches_repo.remove(pitch);
    }

    @Override
    public void updateFootballPitch(String id, FootballPitchDTO o) throws Base_Exception {
        for (PitchRental r : rentals.getAll()) {
            if (r.getPitch().getId().equals(id) && r.getActive()) {
                throw new Pitch_Manager_Exception("Cannot update pitch while it has reservations");
            }
        }
        pitches_repo.update(id, PitchMapper.footballPitchFromDTO(o));
    }

    @Override
    public void updateBasketballPitch(String id, BasketballPitchDTO o) throws Base_Exception {
        for (PitchRental r : rentals.getAll()) {
            if (r.getPitch().getId().equals(id) && r.getActive()) {
                throw new Pitch_Manager_Exception("Cannot update pitch while it has reservations");
            }
        }
        pitches_repo.update(id, PitchMapper.basketballPitchFromDTO(o));
    }

    @Override
    public List<FootballPitchDTO> getAllFootballPitches() {
        List<Pitch> pitches = this.pitches_repo.getAll().stream().filter( p -> p instanceof FootballPitch).collect(Collectors.toList());
        List<FootballPitchDTO> res = new ArrayList<>();
        for (Pitch fp : pitches) {
            res.add(PitchMapper.footballPitchToDTO((FootballPitch) fp));
        }
        return res;
    }

    @Override
    public List<BasketballPitchDTO> getAllBasketballPitches() {
        List<Pitch> pitches = this.pitches_repo.getAll().stream().filter( p -> p instanceof BasketballPitch).collect(Collectors.toList());
        List<BasketballPitchDTO> res = new ArrayList<>();
        for (Pitch fp : pitches) {
            res.add(PitchMapper.basketballPitchToDTO((BasketballPitch) fp));
        }
        return res;
    }
}
