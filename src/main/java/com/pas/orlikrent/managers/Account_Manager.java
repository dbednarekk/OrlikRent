package com.pas.orlikrent.managers;

import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ApplicationScoped //todo check scope
@NoArgsConstructor
public class Account_Manager implements IAccount_Manager {

    @Inject
    private IAccount_Repo accountRepository;

    @Override
    public List<Account> getAll() {
        return this.accountRepository.getAll();
    }

    @Override
    public Account getByID(String id) throws Base_Exception {
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
    public void update(String id, Account o) throws Base_Exception {
        this.accountRepository.update(id, o);
    }

    public void active_account(String login) {
        this.accountRepository.active_account(login);
    }

    public void deactive_account(String login) {
        this.accountRepository.deactive_account(login);
    }

    @Override
    public List<Account> getAllClients() {
        List<Account> clients = this.getAll().stream().filter(person -> person instanceof Client).collect(Collectors.toList());
        return clients;
    }

    @Override
    public  List<Account> getAllManagers() {
        List<Account> managers = this.getAll().stream().filter(person -> person instanceof Manager).collect(Collectors.toList());
        return managers;
    }

    @Override
    public  List<Account> getAllAdmins() {
        List<Account> admins = this.getAll().stream().filter(person -> person instanceof Admin).collect(Collectors.toList());
        return admins;
    }
}
