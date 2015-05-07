/**
 * JacksonFeature.java
 * Created on Aug 28, 2014, 5:35:28 PM
 *
 * TripDashboard
 * TripDashboard
 *
 * Copyright (c) Trip Dashboard - www.tripdashboard.pt
 */
package eu.lpinto.petshelter.api.util;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * THis class is just used to configure glassfish in order to favor jackson instead of moxy.
 *
 * @author Luís Pinto <code>- luis.pinto@tripdashboard.pt</code>
 */
public class JacksonFeature implements Feature {

    @Override
    public boolean configure(final FeatureContext context) {
            String postfix = '.' + context.getConfiguration().getRuntimeType().name().toLowerCase();
            context.property( org.glassfish.jersey.CommonProperties.MOXY_JSON_FEATURE_DISABLE + postfix, true );

            context.register( JsonParseExceptionMapper.class );
            context.register( JsonMappingExceptionMapper.class );
            context.register( JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class );

            return true;
    }
}
