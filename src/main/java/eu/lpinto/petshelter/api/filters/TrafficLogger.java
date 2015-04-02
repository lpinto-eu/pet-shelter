package eu.lpinto.petshelter.api.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs the API requests and responses.
 *
 * @author Luís Pinto <code>luis-m-pinto@ext.ptinovacao.pt</code>
 */
@Provider
@Priority(2)
public class TrafficLogger implements ContainerRequestFilter, ContainerResponseFilter {

    final Logger logger = LoggerFactory.getLogger(TrafficLogger.class);

    /*
     * ContainerRequestFilter
     */
    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        SecurityContext securityContext = requestContext.getSecurityContext();
        String authentication = securityContext.getAuthenticationScheme();
        Principal userPrincipal = securityContext.getUserPrincipal();
        UriInfo uriInfo = requestContext.getUriInfo();
        String method = requestContext.getMethod();
        List<Object> matchedResources = uriInfo.getMatchedResources();
        //...

        StringBuilder sb = new StringBuilder(1000);
        sb.append("----------------------------------------------------------").append(System.lineSeparator());
        sb.append(method).append(" ").append(uriInfo.getAbsolutePath()).append(System.lineSeparator());
        sb.append("---").append(System.lineSeparator());

        sb.append("Headers").append(System.lineSeparator());
        for (Map.Entry<String, List<String>> elem : requestContext.getHeaders().entrySet()) {
            sb.append("\t").append(elem.getKey()).append(" : ").append(elem.getValue()).append(System.lineSeparator());
        }

        sb.append("----------------------------------------------------------").append(System.lineSeparator());

        logger.debug(sb.toString());
    }

    /*
     * ContainerResponseFilter
     */
    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException {
        MultivaluedMap<String, String> stringHeaders = responseContext.getStringHeaders();
        Object entity = responseContext.getEntity();
    }
}
