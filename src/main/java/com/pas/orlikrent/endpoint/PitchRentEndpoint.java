package com.pas.orlikrent.endpoint;

import com.pas.orlikrent.dto.pitch.PitchRentalDTO;
import com.pas.orlikrent.exceptions.Base_Exception;
import com.pas.orlikrent.managers.IPitchRentalManager;
import com.pas.orlikrent.security.ETagFilterBinding;
import com.pas.orlikrent.security.EntityIdentitySignerVerifier;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Rentals")
public class PitchRentEndpoint {
    @Inject
    private IPitchRentalManager iPitchRentalManager;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    public List<PitchRentalDTO> getALlRents() {
        return this.iPitchRentalManager.getAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/Rent/{id}")
    public Response getRentByID(@PathParam("id") String id) throws Base_Exception {
        PitchRentalDTO rent = this.iPitchRentalManager.getByID(id);
        return Response.ok().entity(rent).header("Etag", EntityIdentitySignerVerifier.calculateEntitySignature(rent)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/addRent")
    public Response createRent(PitchRentalDTO rent) throws Base_Exception {
        this.iPitchRentalManager.createRent(rent);
        return Response.status(201).build();
    }
//todo przeniesc logike do managera, zostaiwc pierwszego ifa
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/updateRent/{id}")
    @ETagFilterBinding
    public Response updateRent(@PathParam("id") String id, @HeaderParam("If-Match") @NotNull @NotEmpty String etagValue, PitchRentalDTO rent) {
        if (!EntityIdentitySignerVerifier.verifyEntityIntegrity(rent, etagValue)) {
            return Response.status(406).build();
        }
        try {
            PitchRentalDTO old_rent = iPitchRentalManager.getByID(id);
            this.iPitchRentalManager.update(id, rent);
            return Response.status(204).build();
        } catch (Base_Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/removeRent/{id}")
    public Response deleteRent(@PathParam("id") String id) throws Base_Exception {
        PitchRentalDTO rent = this.iPitchRentalManager.getByID(id);
        this.iPitchRentalManager.remove(rent);
        return Response.status(201).build();
    }

    @PATCH
    @Path("endRental/{id}")
    public Response endRental(@PathParam("id") String id) throws Base_Exception {
        this.iPitchRentalManager.endReservation(id);
        return Response.status(201).build();
    }
}
