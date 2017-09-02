/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.AssignWithFunction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Methods for maps.
 * Objects in the original Lodash. Doesn't really work for objects here,
 * though.
 * @author Justis
 */
public class Maps {
    
    private Maps(){
        throw new IllegalStateException("Cannot instantiate Maps");
    }
    
    /**
     * Add they keys and values of a list of maps to an initial map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to add to.
     * @param sources The list of maps to add to the map.
     * @return The modified map.
     */
    public static <K, V> Map<K, V> assign(Map<K, V> map, List<Map<K, V>> sources){
        Objects.requireNonNull(map);
        Objects.requireNonNull(sources);
        sources.forEach(i -> Objects.requireNonNull(sources));
        sources.forEach(map::putAll);
        return map;
    }
    
    /**
     * Add they keys and values of a list of maps to an initial map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to add to.
     * @param source The list of maps to add to the map.
     * @return The modified map.
     */
    public static <K, V> Map<K, V> assign(Map<K, V> map, Map<K, V> source){
        Objects.requireNonNull(map);
        Objects.requireNonNull(source);
        map.putAll(source);
        return map;
    }
    
    /**
     * Add they keys and values of a list of maps to an initial map, after using a customizer.
     * The customizer receives five parameters:
     * - The value at the current key in the map, or null if none.
     * - The value at the current key in the current source.
     * - The key being evaluated.
     * - An unmodifiable copy of the map.
     * - An unmodifiable copy of the current source.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to modify.
     * @param sources The sources of the keys and values to add.
     * @param customizer The customizer, to calculate the values of each key.
     * @return The modified map
     */
    public static <K, V> Map<K, V> assignWith(Map<K, V> map, List<Map<K, V>> sources, AssignWithFunction<K, V> customizer){
        for(Map<K, V> source : sources){
            for(K key : source.keySet()){
                V customize = customizer.customize(
                        map.getOrDefault(key, null), 
                        source.get(key), 
                        key, 
                        java.util.Collections.unmodifiableMap(map), 
                        java.util.Collections.unmodifiableMap(source));
                map.put(key, customize);
            }
        }
        return map;
    }
}
