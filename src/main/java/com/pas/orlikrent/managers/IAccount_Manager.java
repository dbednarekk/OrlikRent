package com.pas.orlikrent.managers;

import com.pas.orlikrent.dto.accounts.*;
import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Users.Account;

import java.util.List;
import java.util.UUID;

public interface IAccount_Manager  {
    List<AccountDTO> getAll();
    Account getByID(String id) throws Base_Exception;
    AccountDTO getAdminByID(String id) throws Base_Exception;
    ClientDTO getClientByID(String id) throws Base_Exception;
    ManagerDTO getManagerByID(String id) throws Base_Exception;
    AccountDTO getByLogin(String login) throws Base_Exception;
    List<AccountDTO> getByLoginList(String login) throws Base_Exception;
    void addClient(ClientForRegistrationDTO o) throws Base_Exception;
    void addManager(ManagerForRegistrationDTO o) throws Base_Exception;
    void addAdmin(AdminForRegistrationDTO o) throws Base_Exception;

    void remove(String id) throws Base_Exception;

    void updateClient(String id, ClientDTO o) throws Base_Exception;
    void updateManager(String id, ManagerDTO o) throws Base_Exception;
    void updateAdmin(String id, AccountDTO o) throws Base_Exception;

    public void active_deactivate_account(String login) throws Base_Exception;

    public List<ClientDTO> getAllClients();

    public List<ManagerDTO> getAllManagers();

    public List<AccountDTO> getAllAdmins();
}
