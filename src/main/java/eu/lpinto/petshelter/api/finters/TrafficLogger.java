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
//import java.io.IOException;
//import java.security.Principal;
//import java.util.List;
//import java.util.Map;
//import javax.annotation.Priority;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.SecurityContext;
//import javax.ws.rs.core.UriInfo;
//import javax.ws.rs.ext.Provider;
//
///**
// * TODO insert a class description
// *
// * @author Luís Pinto <code>luis-m-pinto@ext.ptinovacao.pt</code>
// */
//@Provider
//@Priority(2)
//public class TrafficLogger implements ContainerRequestFilter, ContainerResponseFilter {
//
////    private static final LogService LOGGER = new LogService(TrafficLogger.class);
//
//    //ContainerRequestFilter
//    @Override
//    public void filter(final ContainerRequestContext requestContext) throws IOException {
//        log(requestContext);
//    }
//
//    //ContainerResponseFilter
//    @Override
//    public void filter(final ContainerRequestContext requestContext,
//                       final ContainerResponseContext responseContext) throws IOException {
//        log(responseContext);
//    }
//
//    /*
//     * LOG
//     */
//    private void log(final ContainerRequestContext requestContext) {
//        SecurityContext securityContext = requestContext.getSecurityContext();
//        String authentication = securityContext.getAuthenticationScheme();
//        Principal userPrincipal = securityContext.getUserPrincipal();
//        UriInfo uriInfo = requestContext.getUriInfo();
//        String method = requestContext.getMethod();
//        List<Object> matchedResources = uriInfo.getMatchedResources();
//        //...
//
//        String log = "\nHeaders";
//
//        for(Map.Entry<String, List<String>> elem : requestContext.getHeaders().entrySet()) {
//            log += ("\n\t" + elem.getKey() + " : " + elem.getValue());
//        }
//
//        System.out.println(log);
////        LOGGER.info(log);
//
//    }
//
//    private void log(final ContainerResponseContext responseContext) {
//        MultivaluedMap<String, String> stringHeaders = responseContext.getStringHeaders();
//        Object entity = responseContext.getEntity();
//        //...
//    }
//}
