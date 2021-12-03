package com.pas.orlikrent.exceptions;

public class ETagException extends Base_Exception {
    public ETagException(String message, Throwable cause) {
        super(message, cause);
    }

    public ETagException(String message) {
        super(message);
    }

    public static ETagException etagIdentityIntegrity() throws ETagException {
        throw new ETagException("Request's ETag header does not match provided data");
    }
}
