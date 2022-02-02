package com.pas.orlikrent.fillers;

import com.pas.orlikrent.dao.Account_Repository;
import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.dao.IPitchRepository;
import com.pas.orlikrent.dao.Pitch_Repository;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.PitchRental;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationFiller {
    @Inject
    IAccount_Repo account_repository;
    @Inject
    IPitchRepository pitchRepository;

    public void fill(List<PitchRental> rents){
        List<Account> clients = this.account_repository.getAll().stream().filter(person -> person instanceof Client).collect(Collectors.toList());

        for (Account ac : clients
             ) {

                rents.add(new PitchRental(ac,pitchRepository.getAll().get(new Random().nextInt(pitchRepository.getAll().size())), LocalDateTime.now().minusHours(24),LocalDateTime.now().minusHours(20)));
                if(ac.getActive()){
                    rents.add(new PitchRental(ac,pitchRepository.getAll().get(new Random().nextInt(pitchRepository.getAll().size())), LocalDateTime.now().minusHours(48),LocalDateTime.now().minusHours(45)));

            }
        }
        for (PitchRental r: rents
             ) {
            r.setId(UUID.randomUUID().toString());
        }
    }
}
