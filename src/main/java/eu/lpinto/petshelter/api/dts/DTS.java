package eu.lpinto.petshelter.api.dts;

/**
 *
 * @author yrs
 */
public interface DTS<I, O> {
    
    O tranform(I in);
}
