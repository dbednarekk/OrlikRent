package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.model.Users.Account;

import java.util.UUID;

public interface IAccount_Repo extends IRepository<Account, String>{

    public void active_account(String login);
    public void deactivate_account(String login);
    public Account getByLogin(String login) throws Account__Exception;
}
