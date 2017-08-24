/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.List;

/**
 * A mapper used in array mapping.
 * @author justislamanna
 * @param <T> The type in the list. 
 * @param <R> The type output from the mapper.
 */
public interface ArrayFunction<T, R> {
    
    /**
     * Maps a value.
     * @param value The value to test.
     * @param index The index of the value.
     * @param list The list that is being tested.
     * @return The new value.
     */
    R map(T value, int index, List<T> list);
}
