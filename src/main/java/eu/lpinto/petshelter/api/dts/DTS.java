package eu.lpinto.petshelter.api.dts;

/**
 *
 * @author mostardinha - pedro.mostardinha@gmail.com
 */
public interface DTS<I, O> {
    
    O transform(I in);
}
