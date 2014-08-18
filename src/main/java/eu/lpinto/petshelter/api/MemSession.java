/**
 * SessionDB.java
 * Created on 14-Aug-2014, 19:23:06
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.api;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory database for sessions.
 *
 * @author Luís Pinto - mail@lpinto.eu
 */
public enum MemSession {

    DB;

    private final Map<String, Integer> sessions = new HashMap<>(10);

    public void put(final String session, final int userID) {
        sessions.put(session, userID);
    }

    public Integer getUserID(final String session) {
        return sessions.get(session);
    }
}
