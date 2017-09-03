/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.AssignWithFunction;
import java.util.ArrayList;
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
     * Internal function to assign a source to a map through a customizer.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to apply to.
     * @param source The source map to apply to the map.
     * @param customizer The customizer to use.
     */
    static <K, V> void iAssignWith(Map<K, V> map, Map<K, V> source, AssignWithFunction<K, V> customizer){
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
        Objects.requireNonNull(map);
        Objects.requireNonNull(sources);
        sources.forEach(i -> Objects.requireNonNull(sources));
        Objects.requireNonNull(customizer);
        for(Map<K, V> source : sources){
            iAssignWith(map, source, customizer);
        }
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
     * @param source The sources of the keys and values to add.
     * @param customizer The customizer, to calculate the values of each key.
     * @return The modified map
     */
    public static <K, V> Map<K, V> assignWith(Map<K, V> map, Map<K, V> source, AssignWithFunction<K, V> customizer){
        Objects.requireNonNull(map);
        Objects.requireNonNull(source);
        Objects.requireNonNull(customizer);
        iAssignWith(map, source, customizer);
        return map;
    }
    
    /**
     * Extracts a list of values from a list of keys.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to pull from.
     * @param keys The list of keys to retrieve.
     * @return The list of values corresponding to the provided keys.
     */
    public static <K, V> List<V> at(Map<K, V> map, List<K> keys){
        Objects.requireNonNull(map);
        Objects.requireNonNull(keys);
        keys.forEach(i -> Objects.requireNonNull(i));
        List<V> at = new ArrayList<>();
        for(K key : keys){
            at.add(map.get(key));
        }
        return at;
    }
    
    /**
     * Retrieve a list of values from a list of indexes.
     * @param <T> The type in the list.
     * @param list The list to pull from.
     * @param indexes The indexes to pull.
     * @return The values corresponding to the provided indexes.
     */
    public static <T> List<T> at(List<T> list, List<Integer> indexes){
        Objects.requireNonNull(list);
        Objects.requireNonNull(indexes);
        indexes.forEach(i -> Objects.requireNonNull(i));
        List<T> at = new ArrayList<>();
        for(int index : indexes){
            if(index < list.size()) {
                at.add(list.get(index));
            }
        }
        return at;
    }
    
    /**
     * Assigns any key/value pairs not in object to their defaults.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param object The object to modify.
     * @param defaults The default values.
     * @return The object, with default values added if needed.
     */
    public static <K, V> Map<K, V> defaults(Map<K, V> object, Map<K, V> defaults){
        for(K key : defaults.keySet()){
            if(!object.containsKey(key)){
                object.put(key, defaults.get(key));
            }
        }
        return object;
    }
}   
