package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.fillers.DataFiller;
import com.pas.orlikrent.model.Users.Account;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@ApplicationScoped
public class Account_Repository implements IAccount_Repo {

    private final List<Account> accounts = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        DataFiller filer = new DataFiller();
        filer.fillAccounts(accounts);
    }

    public List<Account> getAll() {
        synchronized (this.accounts) {
            return accounts;
        }
    }

    public Account getByID(String id) throws Account__Exception {
        synchronized (this.accounts){
        for (Account ac : this.accounts) {
            if (ac.getId().equals(id)) {
                return ac;
            }
        }
        throw new Account__Exception("Cannot find account with given id");
    }}

    public Account getByLogin(String login) throws Account__Exception {
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().contains(login)) {
                    return ac;
                }
            }
            throw new Account__Exception("Cannot find account with given login");
        }
    }
    public List<Account> getByLoginList(String login) throws Account__Exception {
        List<Account> res = new ArrayList<>();
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().contains(login)) {
                   res.add(ac);
                }
            }
           return res;
        }
    }

    public void add(Account acc) throws Account__Exception {
        synchronized (this.accounts) {
            acc.setId(UUID.randomUUID().toString());
            for (Account ac : accounts) {
                if (ac.getLogin().equals(acc.getLogin() )) {
                    throw new Account__Exception("User with given login already exits, please choose another login");
                }
            }
            accounts.add(acc);
        }
    }

    public void remove(Account acc) throws Account__Exception {

    }

    public void update(String oldAccount, Account newAccount) {
        synchronized (this.accounts) {
            for (int i = 0; i < accounts.size(); i++) {
                if (oldAccount.equals(accounts.get(i).getId())) {
                    newAccount.setPassword(accounts.get(i).getPassword());
                    this.accounts.set(i, newAccount);
                }
            }
        }
    }

    public void active_account(String login) {
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().equals(login)) {
                    ac.setActive(true);
                }
            }
        }
    }

    public void deactivate_account(String login) {
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().equals(login)) {
                    ac.setActive(false);
                }
            }
        }
    }
    public void resetPassword(String login, String oldPasswd, String newPasswd) throws Base_Exception {
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().equals(login)) {
                    if(!ac.getPassword().equals(oldPasswd)){
                        throw new Base_Exception("Password does not match");
                    }
                    if(ac.getPassword().equals(newPasswd)){
                        throw new Base_Exception("Old password is the same as new one");
                    }
                    ac.setPassword(newPasswd);
                }
            }
        }
    }
}
