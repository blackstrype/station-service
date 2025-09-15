package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/status")
public class StatusResource {

    @ConfigProperty(name = "quarkus.application.version")
    String version;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return version;
    }
}
