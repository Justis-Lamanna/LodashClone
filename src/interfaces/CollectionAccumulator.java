/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * Parent of collection-based accumulators.
 * @author Justis
 * @param <V> The type the value is.
 * @param <I> The type the id is (index/key).
 * @param <C> The type the collection is.
 * @param <R> The type the value accumulates into.
 */
public interface CollectionAccumulator<V, I, C, R> {
    R reduce(R reduction, V value, I key, C collection);
}
