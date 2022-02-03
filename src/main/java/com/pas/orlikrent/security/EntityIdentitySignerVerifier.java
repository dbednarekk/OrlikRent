package com.pas.orlikrent.security;



import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.pas.orlikrent.exceptions.ETagException;
import java.text.ParseException;
import java.util.Properties;

public class EntityIdentitySignerVerifier {
    private static final Properties securityProperties = PropertiesReader.getSecurityProperties();


    public static String calculateEntitySignature(SignableEntity entity) throws ETagException {
        try {
            JWSSigner signer = new MACSigner(securityProperties.getProperty("etag.secret"));

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(entity.getSignablePayload()));
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new ETagException("Request's ETag header does not match provided data", e);
        }
    }


    public static boolean validateEntitySignature(String tag) {
        try {
            JWSObject jwsObject = JWSObject.parse(tag);
            JWSVerifier verifier = new MACVerifier(securityProperties.getProperty("etag.secret"));
            return jwsObject.verify(verifier);

        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean verifyEntityIntegrity(SignableEntity entity, String tag) {
        try {
            final String header = JWSObject.parse(tag).getPayload().toString();
            final String signableEntityPayload = entity.getSignablePayload();
            return validateEntitySignature(tag) && signableEntityPayload.equals(header);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}

