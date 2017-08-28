/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * Parent of collection-based consumers
 * @author Justis
 * @param <V> The type the values are
 * @param <I> The type the identifiers are (index/keys)
 * @param <C> The type the collection is
 */
public interface CollectionConsumer<V, I, C> {
    
    /**
     * Consumes a value.
     * @param value The value to consume.
     * @param id The key of the value.
     * @param collection The collection containing the key and value.
     */
    void accept(V value, I id, C collection);
}
