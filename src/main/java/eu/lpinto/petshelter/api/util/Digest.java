/**
 * Digest.java
 * Created on 24-Aug-2014, 16:28:00
 *
 * petshelter-webapp
 * petshelter-webapp
 *
 * Copyright (c) Pet Shelter - www.petshelter.info
 */

package eu.lpinto.petshelter.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO insert a class description
 *
 * @author Luís Pinto -  mail@lpinto.eu
 */
public final class Digest {
    
    public static String getSHA(String passwordToHash) {
        return getSHA(passwordToHash, null);
    }
    
    public static String getSHA(String passwordToHash, String salt) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-512");

            if (salt != null) {
                digester.update(salt.getBytes());
            }

            byte[] digest = digester.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder(512);
            
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private Digest() {
        throw new AssertionError("Private Constructor.");
    }

}
