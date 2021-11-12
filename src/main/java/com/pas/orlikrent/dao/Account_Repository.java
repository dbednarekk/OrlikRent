package com.pas.orlikrent.dao;

import com.pas.orlikrent.exceptions.Account_Repo_Exception;
import com.pas.orlikrent.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Account_Repository {

    private List<Account> accounts = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        accounts.add(new Account("dbednarek", "abcABC123*", "bednarek@gmail.com", "Damian", "Bednarek", "ADMINISTRATOR"));
        accounts.add(new Account("mklyz", "abcABC123*", "klyz@gmail.com", "Michał", "Kłyż", "MANAGER"));
        accounts.add(new Account("jkowalski", "abcABC123*", "kowalski@gmail.com", "Jan", "Kowalski", "USER"));
        accounts.add(new Account("tnowak", "abcABC123*", "nowak@gmail.com", "Tomasz", "Nowak", "USER"));
        //todo change this
    }

    public List<Account> getAllAccounts() {
        synchronized (this.accounts) {
            return Collections.unmodifiableList(accounts);
        }
    }

    public Account getAccount(UUID id) throws Account_Repo_Exception {
        for (Account ac : accounts) {
            if (ac.getId().equals(id)) {
                return ac;
            }
        }
        throw new Account_Repo_Exception("Cannot find account with given uuid");
    }

    public void addAccount(Account acc) throws Account_Repo_Exception {
        synchronized (this.accounts) {
            for (Account ac : accounts) {
                if (ac.getLogin().equals(acc.getLogin())) {
                    throw new Account_Repo_Exception("User with given login already exits, please choose another login");
                }
            }
            accounts.add(acc);
        }
    }

    public void removeAccount(Account acc) throws Account_Repo_Exception {
        synchronized (this.accounts) {
            try {
                accounts.remove(acc);
            } catch (Exception e) {
                throw new Account_Repo_Exception("Cannot remove given account",e);
            }
        }
    }

    public void updateAccount(UUID oldAccount, Account newAccount) {
        synchronized (this.accounts) {
            for(int i=0;i < accounts.size();i++){
                if(oldAccount.equals(accounts.get(i).getId())){
                    this.accounts.set(i,newAccount);
                }
            }
        }
    }
}
