package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Pitch__Exception;
import com.pas.orlikrent.model.Pitch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class Pitch_Repository implements IRepository<Pitch, String> {

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
