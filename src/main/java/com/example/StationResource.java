package com.example;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("station-reader")
public class StationResource {

    @GET
    public List<Station> list() {
        return Station.listAll();
    }

    @GET
    @Path("/{id}")
    public Station getById(@PathParam("id") Long id) {
        Station station = Station.findById(id);
        if (station == null) {
            throw new NotFoundException();
        }
        return station;
    }
}