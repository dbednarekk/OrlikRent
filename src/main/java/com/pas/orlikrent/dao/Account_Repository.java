package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Account_Repository implements IAccount_Repo {

    private List<Account> accounts = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        accounts.add(new Admin("dbednarek", "abcABC123*", "bednarek@gmail.com", true, "ADMINISTRATOR"));
        accounts.add(new Manager("mklyz", "abcABC123*", "klyz@gmail.com", true, "MANAGER", 2300.0, 12));
        accounts.add(new Client("jkowalski", "abcABC123*", "kowalski@gmail.com", true, "USER", "Jan", "Kowalski"));
        accounts.add(new Client("tnowak", "abcABC123*", "nowak@gmail.com", false, "USER", "Tomasz", "Nowak"));
        for (Account acc : accounts) {
            acc.setId(UUID.randomUUID().toString());
        }
        //todo change this
    }

    public List<Account> getAll() {
        synchronized (this.accounts) {
            return Collections.unmodifiableList(accounts);
        }
    }

    public Account getByID(String id) throws Account__Exception {
        for (Account ac : accounts) {
            if (ac.getId().equals(id)) {
                return ac;
            }
        }
        throw new Account__Exception("Cannot find account with given id");
    }

    public Account getByLogin(String login) throws Account__Exception {
        for (Account ac : accounts) {
            if (ac.getLogin().contains(login)) {
                return ac;
            }
        }
        throw new Account__Exception("Cannot find account with given login");

    }

    public void add(Account acc) throws Account__Exception {
        synchronized (this.accounts) {
            acc.setId(UUID.randomUUID().toString());
            for (Account ac : accounts) {
                if (ac.getLogin().equals(acc.getLogin())) {
                    throw new Account__Exception("User with given login already exits, please choose another login");
                }
            }
            accounts.add(acc);
        }
    }

    public void remove(Account acc) throws Account__Exception {
        synchronized (this.accounts) {
            try {
                accounts.remove(acc);
            } catch (Exception e) {
                throw new Account__Exception("Cannot remove given account", e);
            }
        }
    }

    public void update(String oldAccount, Account newAccount) {
        synchronized (this.accounts) {
            for (int i = 0; i < accounts.size(); i++) {
                if (oldAccount.equals(accounts.get(i).getId())) {
                    this.accounts.set(i, newAccount);
                }
            }
        }
    }

    public void active_account(String login) {
        for (Account ac : accounts) {
            if (ac.getLogin().equals(login)) {
                ac.setActive(true);
            }
        }
    }

    public void deactive_account(String login) {
        for (Account ac : accounts) {
            if (ac.getLogin().equals(login)) {
                ac.setActive(false);
            }
        }
    }
}
