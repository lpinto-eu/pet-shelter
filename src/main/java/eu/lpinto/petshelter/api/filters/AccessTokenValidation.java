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

import eu.lpinto.petshelter.aaa.MemSession;
import eu.lpinto.petshelter.api.dto.Error;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Priority;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
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

    private static final String AuthorizationHeader = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String USER_ID = "userID";
    private static final String ADMIN_BEARER = "PetAdmin";
    private static final Map<String, String> dmzEndpoints;

    static {
        dmzEndpoints = new HashMap<>(4);
        dmzEndpoints.put("", HttpMethod.GET);
        dmzEndpoints.put("/", HttpMethod.GET);
        dmzEndpoints.put("/session", HttpMethod.POST);
        dmzEndpoints.put("/users", HttpMethod.POST);
    }

    //ContainerRequestFilter
    @Override
    public void filter(final ContainerRequestContext requestContext) {
        if (dmz(requestContext)) {
            return;
        }

        String authToken = requestContext.getHeaderString(AuthorizationHeader);

        if (authToken == null) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new Error("Missing Authorization token."))
                    .type(MediaType.APPLICATION_JSON)
                    .build());
            System.out.println("Missing Authorization token.");

        } else if (authToken.isEmpty() || !authToken.startsWith(BEARER)) {
            requestContext
                    .abortWith(Response
                            .status(Response.Status.UNAUTHORIZED)
                            .type(MediaType.APPLICATION_JSON)
                            .entity(new Error("Invalid Authorization token."))
                            .build());
            System.out.println("Invalid Authorization Token.");

        } else {

            /* Session ID */
            String bearerToken = authToken.substring(BEARER.length());
            try {

                if (bearerToken.isEmpty()) {
                    requestContext
                            .abortWith(Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity(new Error("Empty access token."))
                                    .type(MediaType.APPLICATION_JSON)
                                    .build());
                    System.out.println("Empty access token.");

                } else if (bearerToken.startsWith(ADMIN_BEARER)) {
                    requestContext.getHeaders().add(USER_ID, String.valueOf(Integer.valueOf(bearerToken.substring(ADMIN_BEARER.length(), bearerToken.length()))));

                } else {

                    /* User ID */
                    Integer userID = MemSession.DB.getUserID(bearerToken);

                    if (userID == null) {
                        requestContext
                                .abortWith(Response
                                        .status(Response.Status.UNAUTHORIZED)
                                        .entity(new Error("Unknown access token."))
                                        .type(MediaType.APPLICATION_JSON)
                                        .build());
                        System.out.println("Unknown access token.");

                    } else {
                        requestContext.getHeaders().add(USER_ID, String.valueOf(userID));
                    }
                }

            } catch (RuntimeException ex) {
                requestContext
                        .abortWith(Response
                                .status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(new Error("Cannot validate token. Error: " + ex.getLocalizedMessage() + "."))
                                .type(MediaType.APPLICATION_JSON)
                                .build());
                System.out.println("Cannot validate token.");
            }
        }
    }

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
        // Empty
    }

    private static boolean dmz(final ContainerRequestContext requestContext) {
        String absolutePath = requestContext.getUriInfo().getAbsolutePath().toString();
        String method = requestContext.getRequest().getMethod();

        String[] service = absolutePath.split("/api");

        if (service.length == 1) {
            return true; // root endpoint without '/'
        }

        return dmzEndpoints.get(service[1]) != null && dmzEndpoints.get(service[1]).equals(method);
    }
}
