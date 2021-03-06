package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.dto.accounts.*;
import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Account__Exception;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IAccount_Manager;
import com.pas.orlikrent.managers.converters.AccountMapper;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.model.Users.Admin;
import com.pas.orlikrent.model.Users.Client;
import com.pas.orlikrent.model.Users.Manager;
import com.pas.orlikrent.security.ETagFilterBinding;
import com.pas.orlikrent.security.EntityIdentitySignerVerifier;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

//@RequestScoped
@Path("/Account")
public class Account_endpoint {

    @Inject
    private IAccount_Manager accountManager;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public List<AccountDTO> getAllAccounts() {
        return accountManager.getAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/clients")
    public List<ClientDTO> getAllClients() {
        return accountManager.getAllClients();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/managers")
    public List<ManagerDTO> getAllManagers() {
        return accountManager.getAllManagers();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/admins")
    public List<AccountDTO> getAllAdmins() {
        return accountManager.getAllAdmins();
    }

    @Produces({MediaType.APPLICATION_JSON})
    @Path("/logins/{login}")
    public List<AccountDTO> getAccountsByLoginList(@PathParam("login") String login) throws Base_Exception {
        return accountManager.getByLoginList(login);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login/{login}")
    public Response getAccountByLogin(@PathParam("login") String login) throws Base_Exception {
        AccountDTO account = accountManager.getByLogin(login);
        return Response.ok().entity(account).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(account)).build();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/client/{id}")
    public Response getClientByID(@PathParam("id") String id) throws Base_Exception {
        ClientDTO account = accountManager.getClientByID(id);
        return Response.ok().entity(account).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(account)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/manager/{id}")
    public Response getManagerByID(@PathParam("id") String id) throws Base_Exception {
        ManagerDTO account = accountManager.getManagerByID(id);
        return Response.ok().entity(account).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(account)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/admin/{id}")
    public Response getAccountByID(@PathParam("id") String id) throws Base_Exception {
        AccountDTO account = accountManager.getAdminByID(id);
        return Response.ok().entity(account).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(account)).build();
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/client")
    public Response createClient(@Valid @NotNull ClientForRegistrationDTO clientDTO) throws Base_Exception {
            this.accountManager.addClient(clientDTO);
            return Response.status(201).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/manager")
    public Response createManager(@Valid @NotNull ManagerForRegistrationDTO manager) throws Base_Exception {

            this.accountManager.addManager(manager);
            return Response.status(201).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/admin")
    public Response createAdmin(@Valid @NotNull AdminForRegistrationDTO admin) throws Base_Exception {

            this.accountManager.addAdmin(admin);
            return Response.status(201).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/AccountActivation/{login}")
    public Response de_activeAccount(@NotNull @PathParam("login") String login) throws Base_Exception {

            accountManager.active_deactivate_account(login);
            return Response.status(201).build();

    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/UpdateClient/{id}")
    @ETagFilterBinding
    public Response updateClient(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue,  @Valid @NotNull ClientDTO client) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(client, etagValue)) {
            return Response.status(412).build();
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
            this.accountManager.updateClient(id, client);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/UpdateManager/{id}")
    @ETagFilterBinding
    public Response updateManager(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue,@Valid @NotNull ManagerDTO manager) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(manager, etagValue)) {
            return Response.status(412).build();
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
            this.accountManager.updateManager(id, manager);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/UpdateAdmin/{id}")
    @ETagFilterBinding
    public Response updateAdmin(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue,@Valid @NotNull AccountDTO admin) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(admin, etagValue)) {
            return Response.status(412).build();
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
            this.accountManager.updateAdmin(id, admin);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/resetPassword")
    @ETagFilterBinding
    public Response resetPassword(ResetPasswdDTO resetPasswdDTO, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue) throws ParseException, Base_Exception {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(resetPasswdDTO, etagValue)) {
            return Response.status(412).build();
        }
        this.accountManager.resetPassword(resetPasswdDTO);
        return Response.status(200).build();
    }
}

