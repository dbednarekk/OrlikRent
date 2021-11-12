package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Pitch_Repo_Exception;
import com.pas.orlikrent.model.Pitch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Pitch_Repository {

    private List<Pitch> pitches = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        //todo init data
    }

    public List<Pitch> getAllPitches() {
        synchronized (this.pitches) {
            return Collections.unmodifiableList(pitches);
        }
    }

    public Pitch getPitch(UUID id) throws Pitch_Repo_Exception {
        for (Pitch pitch : pitches) {
            if (pitch.getId().equals(id)) {
                return pitch;
            }
        }
        throw new Pitch_Repo_Exception("Cannot find pitch with given uuid");
    }

    public void addPitch(Pitch pitch) throws Pitch_Repo_Exception {
        synchronized (this.pitches) {
            for (Pitch p : pitches) {
                if (pitches.contains(p)) {
                    throw new Pitch_Repo_Exception("There is pitch already exists");
                }
            }
            pitches.add(pitch);
        }
    }

    public void removePitch(Pitch pitch) throws Pitch_Repo_Exception {
        synchronized (this.pitches) {
            try {
                pitches.remove(pitch);
            } catch (Exception e) {
                throw new Pitch_Repo_Exception("Cannot remove given pitch ", e);
            }
        }
    }

    public void updatePitch(UUID oldPitch, Pitch newPitch) {
        synchronized (this.pitches) {
            for (int i = 0; i < pitches.size(); i++) {
                if (oldPitch.equals(pitches.get(i).getId())) {
                    this.pitches.set(i, newPitch);
                }
            }
        }
    }
}
