/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 * A mapper used in array mapping.
 * @author justislamanna
 * @param <T> The type in the list. 
 * @param <R> The type output from the mapper.
 */
public interface ArrayFunction<T, R> extends CollectionFunction<T, Integer, List<T>, R> {
    
    /**
     * The identity function, which returns the input value every time.
     * @param <T> The type of the input and output.
     * @return The identity ArrayFunction.
     */
    public static <T> ArrayFunction<T, T> identity(){
        return (v, i, a) -> v;
    }
}
