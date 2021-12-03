package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IAccount_Manager;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Path("/Account")
public class Account_endpoint {

    @Inject
    private IAccount_Manager accountManager;

    @GET
    @Path("/")
    public List<Account> getAllAccounts(){
        return accountManager.getAll();
    }
    @GET
    @Path("/clients")
    public List<Account> getAllClients(){
        return  accountManager.getAllClients();
    }
    @GET
    @Path("/managers")
    public List<Account> getAllManagers(){
        return  accountManager.getAllManagers();
    }
    @GET
    @Path("/admins")
    public List<Account> getAllAdmins(){
        return  accountManager.getAllAdmins();
    }

    @GET
    @Path("/{id}")
    public Client getClientById(@PathParam("id") String id) throws Base_Exception {
        Client client = (Client) accountManager.getByID(id);
        return client;
    }
    @POST
    @Path("client")

    public void createClient()

//    @GET
//    @Path("/{id}")
//    public Client addClient(@PathParam("id") String id){
//        return new Client(id);
//    }
}

