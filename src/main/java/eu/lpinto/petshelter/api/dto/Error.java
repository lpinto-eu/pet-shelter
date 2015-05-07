/**
 * Error.java
 * Created on 07-May-2015, 07:54:34
 *
 * Pet Shelter
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */
package eu.lpinto.petshelter.api.dto;

import java.io.Serializable;

/**
 * Encapsulates an error message.
 *
 * @author Luis Pinto <luis.pinto@petshelter.info>
 */
public class Error implements Serializable {

    public String message;

    public Error() {
        super();
    }

    public Error(final String message) {
        this.message = message;
    }
}
