package com.pas.orlikrent.dao;

import com.pas.orlikrent.model.Users.Account;

import java.util.UUID;

public interface IAccount_Repo extends IRepository<Account, UUID>{

    public void active_account(String login);
    public void deactive_account(String login);
}
