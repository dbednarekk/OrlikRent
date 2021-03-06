package com.pas.orlikrent.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<Base_Exception> {
    @Override
    public Response toResponse(Base_Exception e) {
        return Response.status(BAD_REQUEST).entity(e.getMessage()).build();
    }
}
