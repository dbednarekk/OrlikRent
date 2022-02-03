package com.pas.orlikrent.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.PRECONDITION_FAILED;


@Provider
@ETagFilterBinding
public class ETagFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString("If-Match");
        if (header == null || header.isEmpty()) {
            requestContext.abortWith(Response.status(BAD_REQUEST).entity("etag is empty").build());
        } else if (!EntityIdentitySignerVerifier.validateEntitySignature(header)) {
            requestContext.abortWith(Response.status(PRECONDITION_FAILED).entity("etag is invalid").build());
        }
    }
}
