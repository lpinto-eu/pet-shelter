///*
// * TrafficLogger.java
// * Created on Dec 11, 2013, 5:55:55 PM
// *
// * Segura-Business-API-REST-impl
// * segura-api-rest-impl
// *
// * Copyright (c) PT Inovação S.A.
// */
//package eu.lpinto.petshelter.api.finters;
//
//import javax.annotation.Priority;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import pt.ptinovacao.iam.api.rest.client.aaa.BearerToken;
//import pt.ptinovacao.iam.api.rest.client.data.exceptions.IamServerException;
//import pt.ptinovacao.iam.api.rest.client.data.model.oauth.SessionToken;
//import pt.ptinovacao.iam.api.rest.client.data.model.user.User;
//import pt.ptinovacao.iam.api.rest.client.services.Tokens;
//import pt.ptinovacao.segura.utils.logging.LogService;
//
///**
// * Validates OAuth token token with IAM.
// *
// * @author Luís Pinto <code>luis-m-pinto@ext.ptinovacao.pt</code>
// */
//@Provider
//@Priority(1)
//public class AccessTokenValidation implements ContainerRequestFilter, ContainerResponseFilter {
//
//    private static final LogService LOGGER = new LogService(AccessTokenValidation.class);
//
//    //ContainerRequestFilter
//    @Override
//    public void filter(final ContainerRequestContext requestContext) {
//        String bearerToken = requestContext.getHeaderString("Authorization");
//        if (bearerToken == null) {
//            requestContext.abortWith(Response
//                    .status(Response.Status.UNAUTHORIZED)
//                    .entity("Missing Authorization token.")
//                    .build());
//            LOGGER.warn("Missing Authorization token.");
//
//        } else if (bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")) {
//            requestContext
//                    .abortWith(Response
//                    .status(Response.Status.UNAUTHORIZED)
//                    .entity("Not a valid Bearer token.")
//                    .build());
//            LOGGER.warn("Invalid Access Token.");
//
//        } else {
//            String accessToken = bearerToken.substring("Bearer ".length());
//            try {
//                SessionToken token = new Tokens(new BearerToken(accessToken)).retrieve(accessToken);
//
//                if (token == null) {
//                    requestContext
//                            .abortWith(Response
//                            .status(Response.Status.UNAUTHORIZED)
//                            .entity("accessToken is null.")
//                            .build());
//                    LOGGER.warn("Segura system cannot read user.");
//
//                } else {
//
//                    User user = token.getUser();
//                    if (user != null) {
//                        requestContext.getHeaders().add("userID", user.getId());
//                        requestContext.getHeaders().add("userName", user.getName());
//                    }
//                }
//
//            } catch (IamServerException ex) {
//                if (ex.getHttpCode() == 401) {
//                    requestContext
//                            .abortWith(Response
//                            .status(Response.Status.UNAUTHORIZED)
//                            .entity("User not allowed to use accessTokens service.")
//                            .build());
//                    LOGGER.warn("User not authorized on IAM.");
//                } else {
//                    requestContext
//                            .abortWith(Response
//                            .status(Response.Status.INTERNAL_SERVER_ERROR)
//                            .entity("Cannot validate token. Error: " + ex.getHttpCode())
//                            .build());
//                    LOGGER.warn("Cannot validate token.");
//                }
//            }
//        }
//    }
//
//    @Override
//    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) {
//        // Empty
//    }
//}
