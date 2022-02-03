package com.pas.orlikrent.security;

import com.nimbusds.jwt.SignedJWT;

import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class CustomJWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER = "Bearer ";
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        if (!request.getRequestURL().toString().contains("/api/") ||
                request.getRequestURL().toString().endsWith("/login") ||
                request.getRequestURL().toString().contains("register") ||
                request.getRequestURL().toString().matches("^.*/api/Pitches/footballPitches/|^.*/api/Pitches/basketballPitches/|^.*/api/Rentals/RentsForPitch/.*|^.*/api/Rentals/RentsForClient/.*|^.*/api/auth/register/client|^.*/api/auth/self/.*") ||
                request.getMethod().equals("OPTIONS")) {

            return CORS(CORS(httpMessageContext)).doNothing();
        }
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
            return CORS(httpMessageContext).responseUnauthorized();
        }

        String jwtSerializedToken = authorizationHeader.substring((BEARER.length())).trim();

            try {
                JWTHandler.validateJwtSignature(jwtSerializedToken);
                SignedJWT signedJWT = SignedJWT.parse(jwtSerializedToken);
                String login = signedJWT.getJWTClaimsSet().getSubject();
                String groups = signedJWT.getJWTClaimsSet().getStringClaim("auth");
                Date expirationTime = (Date) (signedJWT.getJWTClaimsSet().getClaim("exp"));

                if (new Date().after(expirationTime)) {
                    return CORS(httpMessageContext).responseUnauthorized();
                }


                return CORS(httpMessageContext).notifyContainerAboutLogin(login, new HashSet<>(Arrays.asList(groups.split(","))));
            } catch (ParseException e) {
                System.err.println(e.getMessage());
                return CORS(httpMessageContext).responseUnauthorized();
            }

    }
    private HttpMessageContext CORS(HttpMessageContext context) {
        context.getResponse().setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        context.getResponse().setHeader("Access-Control-Allow-Headers", "*,access-control-allow-origin,access-control-allow-headers,access-control-allow-credentials,access-control-allow-methods,etag,content-type,content-length,access-control-allow-origin,access-control-allow-headers,access-control-allow-credentials,access-control-allow-methods,etag,content-type,content-length");
        context.getResponse().setHeader("Access-Control-Allow-Credentials", "true");
        context.getResponse().setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD,PATCH");
        context.getResponse().setHeader("Access-Control-Expose-Headers","access-control-allow-origin, access-control-allow-headers, access-control-allow-credentials, access-control-allow-methods, etag, content-type, content-length");
        return context;
    }
}
