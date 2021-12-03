package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IAccount_Manager;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import com.pas.orlikrent.security.ETagFilterBinding;
import com.pas.orlikrent.security.EntityIdentitySignerVerifier;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//@RequestScoped
@Path("/Account")
public class Account_endpoint {

    @Inject
    private IAccount_Manager accountManager;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public List<Account> getAllAccounts() {
        return accountManager.getAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/clients")
    public List<Account> getAllClients() {
        return accountManager.getAllClients();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/managers")
    public List<Account> getAllManagers() {
        return accountManager.getAllManagers();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/admins")
    public List<Account> getAllAdmins() {
        return accountManager.getAllAdmins();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Account getAccountByID(@PathParam("id") String id) throws Base_Exception {
        Account account = accountManager.getByID(id);
        return account;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/client")
    public Response createClient(@NotNull Client client) {
        try {
            this.accountManager.add(client);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(409).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/manager")
    public Response createManager(@NotNull Manager manager) {
        try {
            this.accountManager.add(manager);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(409).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/admin")
    public Response createAdmin(@NotNull Admin admin) {
        try {
            this.accountManager.add(admin);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(409).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/client/{id}")
    @ETagFilterBinding
    public Response updateClient(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, Client client) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(client, etagValue)) {
            return Response.status(406).build();
        }
        try {
            Account old_client = accountManager.getByID(id);
            if (!(old_client instanceof Client))
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.accountManager.update(id, client);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/manager/{id}")
    @ETagFilterBinding
    public Response updateManager(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, Manager manager) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(manager, etagValue)) {
            return Response.status(406).build();
        }
        try {
            Account old_manager = accountManager.getByID(id);
            if (!(old_manager instanceof Manager))
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.accountManager.update(id, manager);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/admin/{id}")
    @ETagFilterBinding
    public Response updateAdmin(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, Admin admin) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(admin, etagValue)) {
            return Response.status(406).build();
        }
        try {
            Account old_admin = accountManager.getByID(id);
            if (!(old_admin instanceof Admin))
                return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.accountManager.update(id, admin);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

