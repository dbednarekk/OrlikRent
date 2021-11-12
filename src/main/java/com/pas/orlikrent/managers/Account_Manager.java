package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Account;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
@ApplicationScoped
@NoArgsConstructor
public class Account_Manager implements IManager<Account, UUID> {

    @Inject
    private IAccount_Repo accountRepository;

    @Override
    public List<Account> getAll() {
        return this.accountRepository.getAll();
    }

    @Override
    public Account getByID(UUID id) throws Base_Exception {
        return this.accountRepository.getByID(id);
    }

    @Override
    public void add(Account o) throws Base_Exception {
        this.accountRepository.add(o);
    }

    @Override
    public void remove(Account o) throws Base_Exception {
        this.accountRepository.remove(o);
    }

    @Override
    public void update(UUID id, Account o) throws Base_Exception {
        this.accountRepository.update(id, o);
    }

    public void active_account(String login) {
        this.accountRepository.active_account(login);
    }

    public void deactive_account(String login) {
        this.accountRepository.deactive_account(login);
    }
}
