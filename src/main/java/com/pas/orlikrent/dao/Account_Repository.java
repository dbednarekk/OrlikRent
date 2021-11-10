package com.pas.orlikrent.dao;

import com.pas.orlikrent.Exceptions.Account_Repo_Exception;
import com.pas.orlikrent.model.Account;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class Account_Repository implements IdentityStore {

    private List<Account> accounts = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    private void InitData() {
        accounts.add(new Account("dbednarek","abcABC123*","bednarek@gmail.com","Damian","Bednarek","ADMINISTRATOR"));
        accounts.add(new Account("mklyz","abcABC123*","klyz@gmail.com","Michał","Kłyż","MANAGER"));
        accounts.add(new Account("jkowalski","abcABC123*","kowalski@gmail.com","Jan","Kowalski","USER"));
        accounts.add(new Account("tnowak","abcABC123*","nowak@gmail.com","Tomasz","Nowak","USER"));
    }

    public List<Account> getAllAccounts(){
        return Collections.unmodifiableList(accounts);
    }
    public Account getAccount(UUID id){
       for( Account ac: accounts){
           if(ac.getId().equals(id)){
               return ac;
           }
       }
       return null;
    }
    public Account addAccount( Account acc) throws Account_Repo_Exception {
        for(Account ac: accounts){
            if( ac.getLogin().equals(acc.getLogin())) {
                throw new Account_Repo_Exception("User with given login already exits, please choose another login");
            }
        }
        accounts.add(acc);
        return acc;
    }
    public void removeAccount(Account acc) throws Account_Repo_Exception {
        try {
            accounts.remove(acc);
        }catch ( Exception e){
            throw new Account_Repo_Exception("Cannot remove given account");
        }
    }
    public void updateAccount(Account oldAccount,Account newAccount ){
        Account from_repo= getAccount(oldAccount.getId());
        from_repo.setId(newAccount.getId());
        from_repo.setPassword(newAccount.getPassword());
        from_repo.setLogin(newAccount.getLogin());
        from_repo.setActive(newAccount.getActive());
        from_repo.setEmail(newAccount.getEmail());
        from_repo.setFirst_name(newAccount.getFirst_name());
        from_repo.setLast_name(newAccount.getLast_name());
        from_repo.setRole(newAccount.getRole());
    }
}
