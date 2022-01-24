package com.pas.orlikrent.dao;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.exceptions.Pitch__Exception;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Stateless
public class Pitch_Repository implements IPitchRepository{

    private final List<Pitch> pitches = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        pitches.add(new FootballPitch("Football Pitch 1 ", 15.50, true, Sector.FULL_SIZE,2,20,true, GroundType.GRASS));
        pitches.add(new BasketballPitch("Basketball Pitch 1",10.0,true,Sector.HALF_SIZE,1,10,2));
        pitches.add(new FootballPitch("Camp Nou",50.0,false,Sector.FULL_SIZE,2,100,false,GroundType.GRANULATE));
        pitches.add(new BasketballPitch("Indoor Basketball Pitch", (double) 0,false,Sector.HALF_SIZE,1,20,5));
        for (Pitch p : pitches) {
            p.setId(UUID.randomUUID().toString());
        }
        //todo change this
    }

    public List<Pitch> getAll() {
        synchronized (this.pitches) {
            return pitches;
        }
    }

    public List<Pitch> getAllTypedPitch() {
        synchronized (this.pitches) {
            return getAll().stream().filter(pitch -> pitch instanceof FootballPitch).collect(Collectors.toList());
        }
    }

    @Override
    public void setRented(String id, boolean t) {
        synchronized (this.pitches) {
            for (Pitch p : pitches
            ) {
                if (p.getId().equals(id)) {
                    p.setRented(t);
                }
            }
        }
    }



    public List<FootballPitch> getAllFootball() {
        synchronized (this.pitches) {
            return (List<FootballPitch>) getAll().stream().filter(pitch -> pitch instanceof FootballPitch);
        }
    }

    public List<BasketballPitch> getAllBasketball() {
        synchronized (this.pitches) {

            return (List<BasketballPitch>) getAll().stream().filter(pitch -> pitch instanceof BasketballPitch);
        }
    }

    public Pitch getByID(String id) throws Pitch__Exception {
        synchronized (this.pitches) {
            for (Pitch pitch : pitches) {
                if (pitch.getId().equals(id)) {
                    return pitch;
                }
            }
            throw new Pitch__Exception("Cannot find pitch with given String");
        }
    }

    public void add(Pitch pitch) throws Pitch__Exception {
        synchronized (this.pitches) {
            for (Pitch p : pitches) {
                if (p.getName().equals(pitch.getName())) {
                    throw new Pitch__Exception("Pitch already exists");
                }
            }
            pitch.setId(UUID.randomUUID().toString());
            pitches.add(pitch);
        }
    }

    public void remove(Pitch pitch) throws Pitch__Exception {
        synchronized (this.pitches) {
            try {
                pitches.remove(pitch);
            } catch (Exception e) {
                throw new Pitch__Exception("Cannot remove given pitch ");
            }
        }
    }

    public void update(String oldPitch, Pitch newPitch) {
        synchronized (this.pitches) {
            for (int i = 0; i < pitches.size(); i++) {
                if (oldPitch.equals(pitches.get(i).getId())) {
                    this.pitches.set(i, newPitch);
                }
            }
        }
    }
}
