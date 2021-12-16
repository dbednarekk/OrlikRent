package com.pas.orlikrent.managers.converters;

import com.pas.orlikrent.dto.accounts.AccountDTO;
import com.pas.orlikrent.dto.accounts.ClientDTO;
import com.pas.orlikrent.dto.accounts.ManagerDTO;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;

public class AccountMapper {
    private AccountMapper(){};

    public static AccountDTO toAccountDTO(Account acc){
        return new AccountDTO(acc.getId(), acc.getLogin(), acc.getEmail(), acc.getActive(),acc.getRole());
    }

    public static ClientDTO toClientDTO(Client client){
        return new ClientDTO(client.getId(), client.getLogin(), client.getEmail(),client.getActive(),
                                client.getRole(),client.getFirst_name(),client.getLast_name());
    }

    public static ManagerDTO toManagerDTO(Manager manager){
        return new ManagerDTO(manager.getId(),manager.getLogin(),manager.getEmail(),manager.getActive(),manager.getRole()
                                ,manager.getSalary(),manager.getNumberOfShifts());
    }

}
