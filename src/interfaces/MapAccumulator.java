/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Map;

/**
 * An accumulator used in map reducing.
 * @author Justis
 * @param <K> The type of the keys.
 * @param <V> The type of the values.
 * @param <R> The type of the accumulated value.
 */
public interface MapAccumulator<K, V, R> extends CollectionAccumulator<V, K, Map<K, V>, R>{
    
}
