package test.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import test.controller.request.GuestRequest;
import test.controller.response.GuestResponse;
import test.domain.Status;
import test.service.FindGuest;
import test.service.SaveGuest;

import java.util.List;

@Path("/guests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class GuestsController {

    private final SaveGuest saveGuest;
    private final FindGuest findGuest;

    @Inject
    public GuestsController(SaveGuest saveGuest, FindGuest findGuest) {
        this.saveGuest = saveGuest;
        this.findGuest = findGuest;
    }

    @POST
    public Response save(GuestRequest guestRequest) {
        var savedGuest = saveGuest.execute(guestRequest.toDomain());
        return Response.status(Response.Status.CREATED)
                .entity(GuestResponse.fromDomain(savedGuest))
                .build();
    }

    @GET
    public Response findGuests(@QueryParam("status") Status status) {
        List<GuestResponse> guests = findGuest.findByStatus(status).stream()
                .map(GuestResponse::fromDomain)
                .toList();
        return Response.ok(guests).build();
    }

    @GET
    @Path("/confirmed")
    public Response findConfirmedGuests() {
        List<GuestResponse> guests = findGuest.findConfirmed().stream()
                .map(GuestResponse::fromDomain)
                .toList();
        return Response.ok(guests).build();
    }
}
