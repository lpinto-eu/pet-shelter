/**
 * Sessions.java
 * Created on 13-Aug-2014, 01:11:42
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.api.services;

import eu.lpinto.petshelter.aaa.MemSession;
import eu.lpinto.petshelter.api.util.Digest;
import eu.lpinto.petshelter.persistence.entities.User;
import eu.lpinto.petshelter.persistence.facades.UserFacade;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
@Singleton
@Path("session")
public class Sessions {

    @EJB
    private UserFacade userFacade;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        if (email == null || email.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new eu.lpinto.petshelter.api.dto.Error("Missing email.")).build();
        }

        if (password == null || password.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new eu.lpinto.petshelter.api.dto.Error("Missing password.")).build();
        }

        User user = userFacade.findByEmail(email);

        if (user == null) {
            System.out.println("Unnown access token.");
            return Response.status(Response.Status.UNAUTHORIZED).entity(new eu.lpinto.petshelter.api.dto.Error("Unnown user.")).build();

        }

        if (!user.getPassword().equals(Digest.getSHA(password))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new eu.lpinto.petshelter.api.dto.Error("Invalid password.")).build();
        }

        String accessToken = String.valueOf(System.currentTimeMillis()) + user.getId();

        String result = "{\"api_key\":{"
                        + "\"access_token\": \"" + accessToken + "\","
                        + "\"user_id\": " + user.getId() + "}}";

        MemSession.DB.put(accessToken, user.getId());

        return Response.ok(result).build();
    }
}
