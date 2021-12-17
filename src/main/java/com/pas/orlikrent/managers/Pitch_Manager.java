package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IPitchRepository;
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
import com.pas.orlikrent.model.Users.Account;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pitch_Manager implements IPitchManager {

    @Inject
    private IPitchRepository pitches_repo;
    @Inject
    IRepository<PitchRental, String> rentals;

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
//     Abstrakta sie chyba nie da
    @Override
        public void add(PitchDTO o) throws Base_Exception {
        Pitch pitch = pitches_repo.getByID(o.getId());
            pitches_repo.add(pitch);
        }

    @Override
    public void addFootballPitch(FootballPitchDTO f) throws Base_Exception {
        pitches_repo.add(PitchMapper.footballPitchFromDTO(f));
    }

    @Override
    public void addBasketballPitch(BasketballPitchDTO b) throws Base_Exception {
        pitches_repo.add(PitchMapper.basketballPitchFromDTO(b));
    }

    @Override
    public void remove(PitchDTO o) throws Base_Exception {
        for(PitchRental r : rentals.getAll()){
            if(r.getPitch().getId().equals(o.getId())){
                throw new Pitch_Manager_Exception("Cannot remove pitch while it has reservations");
            }
        }
        Pitch pitch = pitches_repo.getByID(o.getId());
        pitches_repo.remove(pitch);
    }


    @Override
    public void update(String id, PitchDTO o) throws Base_Exception {
        Pitch pitch = pitches_repo.getByID(o.getId());
        pitches_repo.update(id, pitch);
    }

    @Override
    public void updateFootballPitch(String id, FootballPitchDTO o) throws Base_Exception {
        pitches_repo.update(id, PitchMapper.footballPitchFromDTO(o));
    }

    @Override
    public void updateBasketballPitch(String id, BasketballPitchDTO o) throws Base_Exception {
        pitches_repo.update(id, PitchMapper.basketballPitchFromDTO(o));
    }

    @Override
    public List<FootballPitchDTO> getAllFootballPitches() {
        List<FootballPitchDTO> fpDTO = new ArrayList<>();
        for (FootballPitch fp : this.pitches_repo.getAllFootball()) {
            fpDTO.add(PitchMapper.footballPitchToDTO(fp));
        }
        return fpDTO;
    }

    @Override
    public List<BasketballPitchDTO> getAllBasketballPitches() {
        List<BasketballPitchDTO> bpDTO = new ArrayList<>();
        for (BasketballPitch bp : this.pitches_repo.getAllBasketball()) {
            bpDTO.add(PitchMapper.basketballPitchToDTO(bp));
        }
        return bpDTO;
    }
}
