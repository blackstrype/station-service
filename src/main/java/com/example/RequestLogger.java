package com.example;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class RequestLogger implements ContainerRequestFilter {
    private static final Logger LOG = Logger.getLogger(RequestLogger.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");
        LOG.debugf("Received Authorization header: %s", authHeader);
    }
}
