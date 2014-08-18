/**
 * Sessions.java
 * Created on 13-Aug-2014, 01:11:42
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.api;

import eu.lpinto.petshelter.entities.User;
import eu.lpinto.petshelter.facades.UserFacade;
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
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username_or_email") String userName, @FormParam("password") String password) {
//        if ("pravi".equals(user) && "pravi2015".equals(password)) {
//            return "{\"api_key\": {\"access_token\": \"1\",\"user_id\": 1}}";
//        } else {
//            return "{\"api_key\": {\"access_token\": \"2\",\"user_id\": 2}}";
//        }
        User user = userFacade.findByName(userName);

        if (user != null) {
            String accessToken = String.valueOf(System.currentTimeMillis()) + user.getId();

            String result = "{\"api_key\":{"
                            + "\"access_token\": \"" + accessToken + "\","
                            + "\"user_id\": " + user.getId() + "}}";

            MemSession.DB.put(accessToken, user.getId());

            return Response.ok(result).build();

        } else {
            return Response.status(401).build();
        }
    }
}
