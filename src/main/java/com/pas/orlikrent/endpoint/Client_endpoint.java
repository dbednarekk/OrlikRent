package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.managers.Client_Manager;
import com.pas.orlikrent.model.Users.Client;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Path("/Client")
public class Client_endpoint {

    @Inject
    private Client_Manager clientManager;

    @GET
    @Path("/{id}")
    public List<Client> getAllClients(){
        return new ArrayList<>();
    }

    @GET
    @Path("/{id}")
    public Client getClientById(@PathParam("id") String id){
        Client client = clientManager.getClientById(id);
        return client;
    }

//    @GET
//    @Path("/{id}")
//    public Client addClient(@PathParam("id") String id){
//        return new Client(id);
//    }
}

