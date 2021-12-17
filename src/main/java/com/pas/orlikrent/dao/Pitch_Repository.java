package com.pas.orlikrent.dao;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.exceptions.Pitch__Exception;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Pitch_Repository implements IPitchRepository{

    private List<Pitch> pitches = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        //todo init data
    }

    public List<Pitch> getAll() {
        synchronized (this.pitches) {
            return Collections.unmodifiableList(pitches);
        }
    }

    public List<Pitch> getAllTypedPitch() {
        List<Pitch> collect = getAll().stream().filter(pitch -> pitch instanceof FootballPitch).collect(Collectors.toList());
        return collect;
    }


    // ??? Chyba się nie da
    public List<FootballPitch> getAllFootball() {
        List<FootballPitch> collect = (List<FootballPitch>) getAll().stream().filter(pitch -> pitch instanceof FootballPitch);
        return collect;
    }

    public List<BasketballPitch> getAllBasketball() {
        List<BasketballPitch> collect = (List<BasketballPitch>) getAll().stream().filter(pitch -> pitch instanceof BasketballPitch);
        return collect;
    }

    public Pitch getByID(String id) throws Pitch__Exception {
        for (Pitch pitch : pitches) {
            if (pitch.getId().equals(id)) {
                return pitch;
            }
        }
        throw new Pitch__Exception("Cannot find pitch with given String");
    }

    public void add(Pitch pitch) throws Pitch__Exception {
        synchronized (this.pitches) {
            for (Pitch p : pitches) {
                if (pitches.contains(p)) {
                    throw new Pitch__Exception("Pitch already exists");
                }
            }
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
