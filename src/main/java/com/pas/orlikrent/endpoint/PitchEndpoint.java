package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.dto.pitch.BasketballPitchDTO;
import com.pas.orlikrent.dto.pitch.FootballPitchDTO;
import com.pas.orlikrent.dto.pitch.PitchDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IPitchManager;
import com.pas.orlikrent.model.FootballPitch;
import com.pas.orlikrent.security.ETagFilterBinding;
import com.pas.orlikrent.security.EntityIdentitySignerVerifier;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Pitches")
public class PitchEndpoint {

    @Inject
    private IPitchManager pitchManager;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public List<PitchDTO> getAllPitches() {
        return pitchManager.getAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/footballPitches")
    public List<FootballPitchDTO> getAllFootballPitches() throws Base_Exception {
        return pitchManager.getAllFootballPitches();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/basketballPitches")
    public List<BasketballPitchDTO> getAllBasketballPitches() throws Base_Exception {
        return pitchManager.getAllBasketballPitches();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public Response getPitchByID(@PathParam("id") String id) throws Base_Exception {
        PitchDTO pitch = pitchManager.getByID(id);
        return Response.ok().entity(pitch).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(pitch)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/addFootballPitch")
    public Response createFootballPitch(@NotNull FootballPitchDTO pitch) {
        try {
            this.pitchManager.addFootballPitch(pitch);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(409).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addBasketballPitch")
    public Response createBasketballPitch(@NotNull BasketballPitchDTO pitch) {
        try {
            this.pitchManager.addBasketballPitch(pitch);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(409).build();
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/FootballPitch/{id}")
    @ETagFilterBinding
    public Response updateFootballPitch(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, FootballPitchDTO pitch) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(pitch, etagValue)) {
            return Response.status(406).build();
        }
        try {
            if (!(pitchManager.isPitchFootball(id)))
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.pitchManager.updateFootballPitch(id, pitch);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/BasketballPitch/{id}")
    @ETagFilterBinding
    public Response updateBasketballPitch(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, BasketballPitchDTO pitch) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(pitch, etagValue)) {
            return Response.status(406).build();
        }
        try {
            if (!(pitchManager.isPitchBasketball(id)))
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.pitchManager.updateBasketballPitch(id, pitch);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deletePitch/{id}")
    public Response deletePitch(@PathParam("id") @NotNull String id) {
        try {
            this.pitchManager.remove(id);
            return Response.status(201).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
