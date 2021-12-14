package com.pas.orlikrent.managers;

import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;

import java.util.List;

public interface IPitchManager extends IManager<Pitch, String>{
    List<Pitch> getAllFootballPitches();
    List<Pitch> getAllBasketballPitches();

}
