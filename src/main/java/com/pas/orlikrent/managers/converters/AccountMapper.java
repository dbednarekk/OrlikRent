package com.pas.orlikrent.managers.converters;

import com.pas.orlikrent.dto.accounts.*;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
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
//    public static Account toAccountFromDTO(AccountDTO acc){
//        return new Account(acc.getId(), acc.getLogin(), acc.getEmail(), acc.getActive(),acc.getRole());
//    }

    public static Client toClientFromDTO(ClientForRegistrationDTO client){
        return new Client(client.getLogin(), client.getPassword(), client.getEmail(), true, "USER", client.getFirst_name(),client.getLast_name());
    }
    public static Client toClientFromDTO(ClientDTO client){
        return new Client(client.getId(), client.getLogin(), client.getEmail(),client.getActive(),
                client.getRole(),client.getFirst_name(),client.getLast_name());
    }

    public static Manager toManagerFromDTO(ManagerForRegistrationDTO manager){
        return new Manager(manager.getLogin(),manager.getPassword(), manager.getEmail(),true ,"MANAGER" , manager.getSalary(),manager.getNumberOfShifts());
    }
    public static Manager toManagerFromDTO(ManagerDTO manager){
        Manager manager1 = new Manager();
        manager1.setId(manager.getId());
        manager1.setLogin(manager.getLogin());
        manager1.setEmail(manager.getEmail());
        manager1.setActive(manager.getActive());
        manager1.setRole(manager.getRole());
        manager1.setSalary(manager.getSalary());
        manager1.setNumberOfShifts(manager.getNumberOfShifts());
        return manager1;
    }

    public static Admin toAdminFromDTO(AdminForRegistrationDTO admin){
        return new Admin(admin.getLogin(), admin.getPassword(), admin.getEmail(), true, "ADMINISTRATOR");
    }
    public static Admin toAdminFromDTO(AccountDTO admin){
        Admin admin1 = new Admin();
        admin1.setId(admin.getId());
        admin1.setLogin(admin.getLogin());
        admin1.setEmail(admin.getEmail());
        admin1.setActive(admin.getActive());
        admin1.setRole(admin.getRole());
        return admin1;
    }
}
