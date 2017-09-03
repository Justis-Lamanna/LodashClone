/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Map;

/**
 * A mapper used in 'object' mapping.
 * @author Justis
 * @param <K> The type the keys are.
 * @param <V> The type the values are.
 * @param <R> The type being mapped to.
 */
public interface MapFunction<K, V, R> extends CollectionFunction<V, K, Map<K, V>, R>{
    
    /**
     * The identity function, which always returns the key.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @return The function which always returns the key.
     */
    public static <K, V> MapFunction<K, V, K> keyIdentity(){
        return (v, i, a) -> i;
    }
    
    /**
     * The identity function, which always returns the value.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @return The function which always returns the value.
     */
    public static <K, V> MapFunction<K, V, V> valueIdentity(){
        return (v, i, a) -> v;
    }
}
