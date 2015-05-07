/**
 * RestApplication.java
 * Created on Aug 28, 2014, 5:35:28 PM
 *
 * TripDashboard
 * TripDashboard
 *
 * Copyright (c) Trip Dashboard - www.tripdashboard.pt
 */

package eu.lpinto.petshelter.api.util;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Root configuration of Jersey and the REST API.
 * 
 * @author Luis Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
@javax.ws.rs.ApplicationPath(RestConstants.REST_API_URI)
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register( JacksonFeature.class );
        register(JacksonConfigurator.class);
        packages("eu.lpinto.petshelter.api.services");
        packages("eu.lpinto.petshelter.api.filters");
    }
}