package com.example;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/stations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("station-reader")
public class StationResource {
    @ConfigProperty(name = "simulated.wait.millis", defaultValue = "0")
    private long simulatedWaitMillis;
    @ConfigProperty(name = "simulated.fail.rate", defaultValue = "0")
    private double failRate;

    @GET
    public List<Station> list() {
        return Station.listAll();
    }

    @GET
    @Path("/{id}")
    public Station getById(@PathParam("id") Long id) throws InterruptedException {
        simulateWait();
        simulateFailure();

        Station station = Station.findById(id);
        if (station == null) {
            throw new NotFoundException();
        }
        return station;
    }

    private void simulateWait() throws InterruptedException {
        if (simulatedWaitMillis > 0) {
            long randomMillis = ThreadLocalRandom.current().nextLong(0, simulatedWaitMillis);
            Log.infof("Sleeping for %d ms; Max wait: %d", randomMillis, simulatedWaitMillis);
            Thread.sleep(randomMillis);
        }
    }

    private void simulateFailure() {
        if (failRate > 0) {
            double attempt = ThreadLocalRandom.current().nextDouble();
            if (attempt < failRate) {
                Log.errorf("Simulating failure. Fail rate threshold: %f, attempt: %f", failRate, attempt);
                throw new ServerErrorException(
                        "There was an error while serving your request. Please try again later.",
                        Response.Status.INTERNAL_SERVER_ERROR);
            }
            Log.infof("Simulating success. Fail rate threshold: %f, attempt: %f", failRate, attempt);
        }
    }
}