package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Users.Account;

import java.util.List;
import java.util.UUID;

public interface IAccount_Repo extends IRepository<Account, String>{

     void active_account(String login);
     void deactivate_account(String login);
     Account getByLogin(String login) throws Account__Exception;
     List<Account> getByLoginList(String login) throws Account__Exception;
     void resetPassword(String login, String oldPasswd, String newPasswd) throws Base_Exception;
}
