package com.pas.orlikrent.fillers;

import com.pas.orlikrent.model.BasketballPitch;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import com.pas.orlikrent.model.enums.GroundType;
import com.pas.orlikrent.model.enums.Sector;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class DataFiller {

    public void fillAccounts(List<Account> acc){
        acc.add(new Admin("dbednarek", "abcABC123*", "bednarek@gmail.com", true, "ADMINISTRATOR"));
        acc.add(new Admin("rbranson", "abcABC123*", "branson@gmail.com", false, "ADMINISTRATOR"));
        acc.add(new Manager("mklyz", "abcABC123*", "klyz@gmail.com", true, "MANAGER", 2300.0, 12));
        acc.add(new Manager("jbezos", "abcABC123*", "bezos@gmail.com", false, "MANAGER", 4850.0, 20));
        acc.add(new Client("jkowalski", "abcABC123*", "kowalski@gmail.com", true, "USER", "Jan", "Kowalski"));
        acc.add(new Client("tnowak", "abcABC123*", "nowak@gmail.com", false, "USER", "Tomasz", "Nowak"));
        acc.add(new Client("mwypych", "abcABC123*", "wypych@gmail.com", true, "USER", "Mateusz", "Wypych"));
        acc.add(new Client("json", "abcABC123*", "json@gmail.com", false, "USER", "Jay", "Son"));

        for (Account ac :acc
             ) {
            ac.setId(UUID.randomUUID().toString());
        }
    }
    public  void fillPitches(List<Pitch> pitches){
        pitches.add(new FootballPitch("Salt Lake Stadium", 15.50, true, Sector.FULL_SIZE,2,20,true, GroundType.GRASS));
        pitches.add(new FootballPitch("Camp Nou",50.0,false,Sector.FULL_SIZE,2,100,false,GroundType.GRANULATE));
        pitches.add(new FootballPitch("Emirates Stadium",10.0,true,Sector.HALF_SIZE,2,100,true,GroundType.SILICONE));
        pitches.add(new FootballPitch("Wembley",0.0,false,Sector.HALF_SIZE,1,14,false,GroundType.GRANULATE));
        pitches.add(new FootballPitch("Stade de France",14.99,true,Sector.FULL_SIZE,6,24,true,GroundType.GRASS));
        pitches.add(new FootballPitch("Old Trafford",5.0,true,Sector.FULL_SIZE,6,24,true,GroundType.GRASS));
        pitches.add(new FootballPitch("Allianz Arena",55.0,true,Sector.FULL_SIZE,10,50,true,GroundType.SILICONE));
        pitches.add(new FootballPitch("Santiago Bernabeu",99.99,true,Sector.HALF_SIZE,1,20,false,GroundType.GRANULATE));

        pitches.add(new BasketballPitch("Pigalle",10.0,true,Sector.HALF_SIZE,1,10,2));
        pitches.add(new BasketballPitch("Angels Gate", (double) 0,false,Sector.HALF_SIZE,1,20,5));
        pitches.add(new BasketballPitch("Eurobasket",25.99,true,Sector.FULL_SIZE,5,10,2));
        pitches.add(new BasketballPitch("Mamba House",12.0,true,Sector.FULL_SIZE,1,16,4));
        pitches.add(new BasketballPitch("Esplanade Court",0.0,false,Sector.HALF_SIZE,1,8,1));
        pitches.add(new BasketballPitch("Nike Court",10.0,true,Sector.HALF_SIZE,1,10,2));
        pitches.add(new BasketballPitch("Hall of Fame",99.99,true,Sector.FULL_SIZE,12,55,6));

        for (Pitch p : pitches) {
            p.setId(UUID.randomUUID().toString());
        }
    }
}
