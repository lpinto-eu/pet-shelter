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

import eu.lpinto.petshelter.api.filters.AccessTokenValidation;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Root configuration of Jersey and the REST API.
 *
 * @author Luis Pinto <code>- luis.pinto@petshelter.info</code>
 */
@ApplicationPath(RestConstants.REST_API_URI)
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        register(JacksonFeature.class);
        register(JacksonConfigurator.class);

        register(AccessTokenValidation.class);

        packages("eu.lpinto.petshelter.api.services");

        registerInstances(new LoggingFilter(Logger.getLogger(RestApplication.class.getName()), true));
    }
}
