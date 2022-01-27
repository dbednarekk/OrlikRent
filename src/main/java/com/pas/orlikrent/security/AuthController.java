package com.pas.orlikrent.security;

import com.pas.orlikrent.dto.AuthDTO;

import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
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


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Response authenticate(@NotNull AuthDTO auth){
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
}
