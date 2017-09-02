/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Map;

/**
 * A function used with assignWith
 * @author Justis
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public interface AssignWithFunction<K, V> {
    
    /**
     * The customizer used in an assignWith function.
     * @param objValue The current value at the current key.
     * @param srcValue The value of the source with the current key.
     * @param key The key.
     * @param object The object being modified.
     * @param source The source being used.
     * @return The created value.
     */
    V customize(V objValue, V srcValue, K key, Map<K, V> object, Map<K, V> source);
}
