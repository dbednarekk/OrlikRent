package com.pas.orlikrent.managers;

import com.pas.orlikrent.model.Users.Account;

import java.util.List;
import java.util.UUID;

public interface IAccount_Manager extends IManager<Account, String> {
    public void active_account(String login);

    public void deactive_account(String login);

    public List<Account> getAllClients();

    public List<Account> getAllManagers();

    public List<Account> getAllAdmins();
}
