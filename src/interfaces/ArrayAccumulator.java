/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 * An accumulator used in array reducing.
 * @author Justis
 * @param <T> The type of the initial list.
 * @param <R> The type of the accumulated value.
 */
public interface ArrayAccumulator<T, R> extends CollectionAccumulator<T, Integer, List<T>, R>{
    
}
