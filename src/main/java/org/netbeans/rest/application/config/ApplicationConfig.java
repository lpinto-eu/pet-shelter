/**
 * ApplicationConfig.java
 * Created on Jul 31, 2014, 11:02:27 AM
 *
 * Indicators WS
 * indicators
 *
 * Copyright (c) Trip Dashboard - www.tripdashboard.pt
 */

package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>(1);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(eu.lpinto.petshelter.api.Animals.class);
        resources.add(eu.lpinto.petshelter.api.ClinicalEpisodesTypes.class);
        resources.add(eu.lpinto.petshelter.api.Organizations.class);
        resources.add(eu.lpinto.petshelter.api.Sessions.class);
        resources.add(eu.lpinto.petshelter.api.Users.class);
        resources.add(eu.lpinto.petshelter.api.filters.AccessTokenValidation.class);
        resources.add(eu.lpinto.petshelter.api.filters.TrafficLogger.class);
    }
}
