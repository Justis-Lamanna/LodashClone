/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * Parent of collection-based mapping functions.
 * @author Justis
 * @param <V> The type of the value.
 * @param <I> The type of the identifier (Index/Key)
 * @param <C> The type of collection.
 * @param <R> The type of result.
 */
public interface CollectionFunction<V, I, C, R> {
    
    /**
     * Maps a value
     * @param value The value to map.
     * @param id The ID of the value (index/key)
     * @param collection The collection the value originates from.
     * @return The mapped value.
     */
    R map(V value, I id, C collection);
}
