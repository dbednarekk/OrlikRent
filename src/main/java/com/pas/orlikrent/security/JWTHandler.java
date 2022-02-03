package com.pas.orlikrent.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.security.enterprise.identitystore.CredentialValidationResult;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Properties;

public class JWTHandler {
    private static final Properties securityProperties = PropertiesReader.getSecurityProperties();

    public static String createToken (CredentialValidationResult res){
        try{
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(res.getCallerPrincipal().getName())
                    .claim("auth",String.join(",",res.getCallerGroups()))
                    .issuer("OrlikRent")
                    .expirationTime(java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(60)))
                            .build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(new MACSigner(securityProperties.getProperty("jwt.secret")));
            return signedJWT.serialize();
        } catch (JOSEException e) {
            System.err.println(e.getMessage());
            return "JWT Failure";
        }
    }
    public static boolean validateJwtSignature(String result) {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(result);
            JWSVerifier verifier = new MACVerifier(securityProperties.getProperty("jwt.secret"));
            return signedJWT.verify(verifier);
        } catch (JOSEException | ParseException e) {
            return false;
        }
    }

}
