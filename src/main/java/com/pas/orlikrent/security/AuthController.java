package com.pas.orlikrent.security;

import com.pas.orlikrent.dto.AuthDTO;
import com.pas.orlikrent.dto.accounts.AdminForRegistrationDTO;
import com.pas.orlikrent.dto.accounts.ClientForRegistrationDTO;
import com.pas.orlikrent.dto.accounts.ManagerForRegistrationDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IAccount_Manager;

import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth/")
public class AuthController {
    @Inject
    private IdentityStoreHandler identityStoreHandler;
    @Inject
    private IAccount_Manager accountManager;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Response authenticate(@Valid @NotNull AuthDTO auth){
        Credential credential = auth.toCredential();
        CredentialValidationResult result = identityStoreHandler.validate(credential);
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            String jwtToken = JWTHandler.createToken(result);
            return Response
                    .accepted()
                    .header("Authentication", "Bearer " + jwtToken)
                    .type("application/jwt")
                    .entity(jwtToken)
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity( "Incorrect login or password").build();
        }
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/register/client")
    public Response registerClient(@Valid @NotNull ClientForRegistrationDTO dto) throws Base_Exception {
        this.accountManager.addClient(dto);
        return Response.status(201).build();
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/register/manager")
    public Response registerManager(@Valid @NotNull ManagerForRegistrationDTO dto) throws Base_Exception {
        this.accountManager.addManager(dto);
        return Response.status(201).build();
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/register/admin")
    public Response registerAdmin(@Valid @NotNull AdminForRegistrationDTO dto) throws Base_Exception {
        this.accountManager.addAdmin(dto);
        return Response.status(201).build();
    }
}
