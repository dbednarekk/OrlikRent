package com.pas.orlikrent.security;

import com.pas.orlikrent.dao.IAccount_Repo;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IAccount_Manager;
import com.pas.orlikrent.model.Users.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.metamodel.IdentifiableType;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;
import java.util.HashSet;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {
    @Inject
    private IAccount_Repo accountManager;
    @Override
    public CredentialValidationResult validate (Credential credential){
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
            String givenPassword = usernamePasswordCredential.getPasswordAsString();
            try {
                Account acc = accountManager.getByLogin(usernamePasswordCredential.getCaller());

                if (acc.getPassword().equals(givenPassword) && acc.getActive()) {
                    return new CredentialValidationResult(acc.getLogin(), new HashSet<>(Collections.singletonList(acc.getClass().getSimpleName())));
                }
            }catch (Base_Exception exp){
                return CredentialValidationResult.INVALID_RESULT;
            }
            return CredentialValidationResult.INVALID_RESULT;
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }

}
