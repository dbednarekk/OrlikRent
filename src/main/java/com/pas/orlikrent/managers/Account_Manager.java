package com.pas.orlikrent.managers;

import com.nimbusds.jwt.SignedJWT;
import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.dto.accounts.*;
import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.converters.AccountMapper;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import com.pas.orlikrent.security.JWTHandler;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped //todo check scope
@NoArgsConstructor
public class Account_Manager implements IAccount_Manager {

    @Inject
    private IAccount_Repo accountRepository;

    @Override
    public List<AccountDTO> getAll() {
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac: this.accountRepository.getAll()) {
              res.add(AccountMapper.toAccountDTO(ac));
        }
        return res;
    }

    @Override
    public Account getByID(String id) throws Base_Exception {
        return this.accountRepository.getByID(id);
    }

    @Override
    public AccountDTO getAdminByID(String id) throws Base_Exception {
        return AccountMapper.toAccountDTO(this.accountRepository.getByID(id));
    }

    @Override
    public ClientDTO getClientByID(String id) throws Base_Exception {
        return AccountMapper.toClientDTO((Client) this.accountRepository.getByID(id));
    }

    @Override
    public ManagerDTO getManagerByID(String id) throws Base_Exception {
        return AccountMapper.toManagerDTO((Manager) this.accountRepository.getByID(id));
    }

    @Override
    public AccountDTO getByLogin(String login) throws Base_Exception {
        return AccountMapper.toAccountDTO(this.accountRepository.getByLogin(login));
    }

    @Override
    public List<AccountDTO> getByLoginList(String login) throws Base_Exception {
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac: this.accountRepository.getByLoginList(login)) {
            res.add(AccountMapper.toAccountDTO(ac));
        }
        return res;
    }

    @Override
    public void addClient(ClientForRegistrationDTO o) throws Base_Exception {
        Client client = AccountMapper.toClientFromDTO(o);
        for (Account a : accountRepository.getAll()) {
            if (a.getEmail().equals(client.getEmail()) || a.getLogin().equals(client.getLogin())) {
                throw new Account__Exception("This e-mail or login is taken");
            }
        }
        accountRepository.add(client);
    }

    @Override
    public void addManager(ManagerForRegistrationDTO o) throws Base_Exception {
        Manager manager = AccountMapper.toManagerFromDTO(o);
        for (Account a : accountRepository.getAll()) {
            if (a.getEmail().equals(manager.getEmail()) || a.getLogin().equals(manager.getLogin())) {
                throw new Account__Exception("This e-mail or login is taken");
            }
        }
        accountRepository.add(manager);
    }

    @Override
    public void addAdmin(AdminForRegistrationDTO o) throws Base_Exception {
        Admin admin = AccountMapper.toAdminFromDTO(o);
        for (Account a : accountRepository.getAll()) {
            if (a.getEmail().equals(admin.getEmail()) || a.getLogin().equals(admin.getLogin())) {
                throw new Account__Exception("This e-mail or login is taken");
            }
        }
        accountRepository.add(admin);
    }

    @Override
    public void updateClient(String id, ClientDTO o) throws Base_Exception {
        Client client = AccountMapper.toClientFromDTO(o);
        client.setId(id);
        accountRepository.update(id, client);
    }

    @Override
    public void updateManager(String id, ManagerDTO o) throws Base_Exception {
        accountRepository.update(id, AccountMapper.toManagerFromDTO(o));
    }

    @Override
    public void updateAdmin(String id, AccountDTO o) throws Base_Exception {
        accountRepository.update(id, AccountMapper.toAdminFromDTO(o));
    }

    @Override
    public void remove(String id) throws Base_Exception {

    }

    public void active_deactivate_account(String login) throws Base_Exception {
        if (accountRepository.getByLogin(login).getActive()) {
            this.accountRepository.deactivate_account(login);
        } else {
            this.accountRepository.active_account(login);
        }
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Account> clients = this.accountRepository.getAll().stream().filter(person -> person instanceof Client).collect(Collectors.toList());
        List<ClientDTO> res = new ArrayList<>();
        for (Account ac: clients) {
            res.add(AccountMapper.toClientDTO((Client) ac));
        }
        return res;
    }

    @Override
    public  List<ManagerDTO> getAllManagers() {
        List<Account> managers = this.accountRepository.getAll().stream().filter(person -> person instanceof Manager).collect(Collectors.toList());
        List<ManagerDTO> res = new ArrayList<>();
        for (Account ac: managers) {
            res.add(AccountMapper.toManagerDTO((Manager) ac));
        }
        return res;
    }

    @Override
    public  List<AccountDTO> getAllAdmins() {
        List<Account> admins = this.accountRepository.getAll().stream().filter(person -> person instanceof Admin).collect(Collectors.toList());
        List<AccountDTO> res = new ArrayList<>();
        for (Account ac: admins) {
            res.add(AccountMapper.toAccountDTO(ac));
        }
        return res;
    }
    @Override
    public void resetPassword(ResetPasswdDTO dto ) throws Base_Exception, ParseException {
        if(!JWTHandler.validateJwtSignature(dto.getToken())){
            throw new Base_Exception("Cannot reset password, token incorrect");
        }
        SignedJWT signedJWT = SignedJWT.parse(dto.getToken());
        String login = signedJWT.getJWTClaimsSet().getSubject();
        if(!login.equals(dto.getLogin())){
            throw new Base_Exception("Cannot reset password, logins does not match");
        }
        this.accountRepository.resetPassword(dto.getLogin(), dto.getOldPasswd(), dto.getNewPasswd());

    }
}
