/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * Parent of collection-based predicates.
 * @author Justis
 * @param <V> The type of the value.
 * @param <I> The type of the identifier (Index/Key)
 * @param <C> The type of collection.
 */
public interface CollectionPredicate<V, I, C> {
    
    /**
     * Test a value in a collection
     * @param value The value to test.
     * @param id The identifier (index, or key) to test.
     * @param collection The collection the value is a part of.
     * @return True if the predicate passes.
     */
    boolean test(V value, I id, C collection);
}
