package com.pas.orlikrent.managers.converters;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchDTO;
import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;


public class PitchMapper {
    private PitchMapper(){

    }

    public static FootballPitch footballPitchFromDTO(FootballPitchDTO fbDTO){
        return new FootballPitch(fbDTO.getName(), fbDTO.getPrice(), fbDTO.getLights(), fbDTO.getSector(), fbDTO.getMin_people(),
                fbDTO.getMax_people(), fbDTO.getGoal_nets(), fbDTO.getGrass_type());
    }

    public static BasketballPitch basketballPitchFromDTO(BasketballPitchDTO bfDTO){
        return new BasketballPitch(bfDTO.getName(), bfDTO.getPrice(), bfDTO.getLights(), bfDTO.getSector(), bfDTO.getMin_people(),
                bfDTO.getMax_people(), bfDTO.getNumberOfBaskets());
    }

    public static FootballPitchDTO footballPitchToDTO(FootballPitch fbM){
        return new FootballPitchDTO(fbM.getId(), fbM.getName(), fbM.getPrice(), fbM.getLights(), fbM.getSector(), fbM.getMin_people(), fbM.getMax_people(),
                fbM.getRented(), fbM.getGrass_type(), fbM.getGoal_nets());
    }

    public static BasketballPitchDTO basketballPitchToDTO(BasketballPitch bfM){
        return new BasketballPitchDTO(bfM.getId(), bfM.getName(), bfM.getPrice(), bfM.getLights(), bfM.getSector(), bfM.getMin_people(),
                bfM.getMax_people(), bfM.getRented(), bfM.getNumberOfBaskets());
    }

    public static PitchDTO pitchToDTO(Pitch pM){
        return new PitchDTO(pM.getId(), pM.getName(), pM.getPrice(), pM.getLights(), pM.getSector(), pM.getMin_people(),
                pM.getMax_people(), pM.getRented());
    }

}