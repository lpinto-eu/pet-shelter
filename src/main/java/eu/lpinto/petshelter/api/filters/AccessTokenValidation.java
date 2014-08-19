/*
 * TrafficLogger.java
 * Created on Dec 11, 2013, 5:55:55 PM
 *
 * Segura-Business-API-REST-impl
 * segura-api-rest-impl
 *
 * Copyright (c) PT Inovação S.A.
 */
package eu.lpinto.petshelter.api.filters;

import eu.lpinto.petshelter.api.MemSession;
import java.net.URI;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Validates OAuth token.
 *
 * @author Luís Pinto <code>luis-m-pinto@ext.ptinovacao.pt</code>
 */
@Provider
@Priority(1)
public class AccessTokenValidation implements ContainerRequestFilter, ContainerResponseFilter {

    //ContainerRequestFilter
    @Override
    public void filter(final ContainerRequestContext requestContext) {
        URI absolutePath = requestContext.getUriInfo().getAbsolutePath();
        String method = requestContext.getRequest().getMethod();
        
        System.out.println("----------------------------------------------------------");
        System.out.println("URL: " + absolutePath);
        System.out.println("----------------------------------------------------------");
        
        if(absolutePath.toString().endsWith("api/session") && "POST".equals(method)) {
            return;
        }
        
        if(absolutePath.toString().endsWith("api/users") && "POST".equals(method)) {
            return;
        }
        
        String bearerToken = requestContext.getHeaderString("Authorization");
        if (bearerToken == null) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Missing Authorization token.")
                    .build());
            System.out.println("Missing Authorization token.");

        } else if (bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")) {
            requestContext
                    .abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid Authorization token.")
                            .build());
            System.out.println("Invalid Authorization Token.");

        } else {

            /* Session ID */
            String SessionID = bearerToken.substring("Bearer ".length());
            try {

                if (SessionID.isEmpty()) {
                    requestContext
                            .abortWith(Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity("Empty access token.")
                                    .build());
                    System.out.println("Empty access token.");

                } else {

                    /* User ID */
                    Integer userID = MemSession.DB.getUserID(SessionID);

                    if (userID == null) {
                        requestContext
                                .abortWith(Response
                                        .status(Response.Status.UNAUTHORIZED)
                                        .entity("Unnown access token.")
                                        .build());
                        System.out.println("Unnown access token.");

                    } else {
                        requestContext.getHeaders().add("userID", String.valueOf(userID));
                    }
                }

            }
            catch (RuntimeException ex) {
                requestContext
                        .abortWith(Response
                                .status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity("Cannot validate token. Error: " + ex.getLocalizedMessage())
                                .build());
                System.out.println("Cannot validate token.");
            }
        }
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        // Empty
    }
}
