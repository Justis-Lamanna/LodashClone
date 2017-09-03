/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Map;

/**
 * An interface for map transformation.
 * @author Justis
 * @param <V> The type of the value being accumulated.
 * @param <K> The type of the key being accumulated.
 * @param <R> The type of the accumulator.
 */
public interface MapTransformAccumulator<K, V, R>{
    boolean reduce(R accumulator, V value, K key, Map<K, V> object);
}
