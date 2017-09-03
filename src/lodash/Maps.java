/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.AssignWithFunction;
import interfaces.MapFunction;
import interfaces.MapPredicate;
import interfaces.MapTransformAccumulator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
                    map.get(key), 
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
     * Assigns any key/value pairs not in object to their defaults.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param object The object to modify.
     * @param defaults The default values.
     * @return The object, with default values added if needed.
     */
    public static <K, V> Map<K, V> defaults(Map<K, V> object, Map<K, V> defaults){
        Objects.requireNonNull(object);
        Objects.requireNonNull(defaults);
        for(K key : defaults.keySet()){
            if(!object.containsKey(key)){
                object.put(key, defaults.get(key));
            }
        }
        return object;
    }
    
    /**
     * Find a key in a map that matches a predicate.
     * @param <K> The type of the key.
     * @param object The object to search.
     * @param predicate The predicate to determine a match.
     * @return The found key, or null if none were found.
     */
    public static <K> K findKey(Map<K, ?> object, Predicate<K> predicate){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        for(K key : object.keySet()){
            if(predicate.test(key)){
                return key;
            }
        }
        return null;
    }
    
    /**
     * Iterates over key/value pairs in an object.
     * If the iteratee returns false at any point, the iteration will halt.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to iterate over.
     * @param iteratee The iteratee to use.
     */
    public static <K, V> void forIn(Map<K, V> object, MapPredicate<K, V> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<K, V> immutable = java.util.Collections.unmodifiableMap(object);
        for(K key : object.keySet()){
            if(!iteratee.test(object.get(key), key, immutable)){
                break;
            }
        }
    }
    
    /**
     * Iterates over key/value pairs in an object.
     * If the iteratee returns false at any point, the iteration will halt.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to iterate over.
     * @param iteratee The iteratee to use.
     */
    public static <K, V> void forIn(Map<K, V> object, Predicate<V> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        for(K key : object.keySet()){
            if(!iteratee.test(object.get(key))){
                break;
            }
        }
    }
    
    /**
     * Get the value at the specified key, or a default if none was found.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to retrieve from.
     * @param key The key to retrieve.
     * @param defaultValue The value to return if the object doesn't have the specified key.
     * @return The value for the specified key, or default if not found.
     */
    public static <K, V> V get(Map<K, V> object, K key, V defaultValue){
        Objects.requireNonNull(object);
        return object.getOrDefault(key, defaultValue);
    }
    
    /**
     * Get the value at the specified key.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to retrieve from.
     * @param key The key to retrieve.
     * @return The value for the specified key, or null if not found.
     */
    public static <K, V> V get(Map<K, V> object, K key){
        Objects.requireNonNull(object);
        return object.get(key);
    }
    
    /**
     * Test if the object has the specified key.
     * @param <K> The key to check for.
     * @param object The object to search.
     * @param path The key to search for.
     * @return True if the object has the key.
     */
    public static <K> boolean has(Map<K, ?> object, K path){
        Objects.requireNonNull(object);
        return object.containsKey(path);
    }
    
    /**
     * Creates a map where keys become values and vice versa.
     * @param <K> The type of the key in the input object.
     * @param <V> The type of the value in the input object.
     * @param object The object to invert.
     * @return The inverted object, with key/value pairs reversed.
     */
    public static <K, V> Map<V, K> invert(Map<K, V> object){
        return invertBy(object, v -> v);
    }
    
    /**
     * Creates a map where keys become values, and values go through an iteratee to generate keys.
     * @param <K> The type of the key in the input object.
     * @param <V> The type of the value in the input object.
     * @param <R> The type of the key in the output object.
     * @param object The object to invert.
     * @param iteratee The object to calculate keys from the object values.
     * @return The inverted map.
     */
    public static <K, V, R> Map<R, K> invertBy(Map<K, V> object, Function<V, R> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<R, K> invert = new HashMap<>();
        for(K key : object.keySet()){
            R createdKey = iteratee.apply(object.get(key));
            invert.put(createdKey, key);
        }
        return invert;
    }
    
    /**
     * Get a list of keys in the object.
     * Iteration order is not guaranteed.
     * @param <K> The type of the keys.
     * @param object The object to get keys from.
     * @return The keys of the object.
     */
    public static <K> List<K> keys(Map<K, ?> object){
        Objects.requireNonNull(object);
        return new ArrayList<>(object.keySet());
    }
    
    /**
     * Internal function to map a map.
     * @param <K> The type of key in the source object.
     * @param <L> The type of key in the destination object.
     * @param <V> The type of value in the source object.
     * @param <W> The type of value in the destination object.
     * @param object The object to map.
     * @param keyMap The mapper to convert source keys to destination keys.
     * @param valueMap The mapper to convert source values to destination values.
     * @return The mapped map.
     */
    static <K, L, V, W> Map<L, W> iMapMap(Map<K, V> object, MapFunction<K, V, L> keyMap, MapFunction<K, V, W> valueMap){
        Map<L, W> mapKeys = new HashMap<>();
        for(K key : object.keySet()){
            L mappedKey = keyMap.map(object.get(key), key, object);
            W mappedValue = valueMap.map(object.get(key), key, object);
            mapKeys.put(mappedKey, mappedValue);
        }
        return mapKeys;
    }
    
    /**
     * Maps a map, using a function to generate new keys from the old keys.
     * @param <K> The type of the key in the source map.
     * @param <V> The type of the values.
     * @param <R> The type of the key in the destination map.
     * @param object The object to map.
     * @param iteratee The iterator to map object keys to destination keys.
     * @return The mapped map.
     */
    public static <K, V, R> Map<R, V> mapKeys(Map<K, V> object, MapFunction<K, V, R> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<K, V> immutableObject = java.util.Collections.unmodifiableMap(object);
        return iMapMap(immutableObject, iteratee, MapFunction.valueIdentity());
    }
    
    /**
     * Maps a map, using a function to generate new keys from the old keys.
     * Unlike other methods, the Function accepted by this recieves the key
     * to modify, rather than the value as in all other methods like this.
     * @param <K> The type of the key in the source map.
     * @param <V> The type of the values.
     * @param <R> The type of the key in the destination map.
     * @param object The object to map.
     * @param iteratee The iterator to map object keys to destination keys.
     * @return The mapped map.
     */
    public static <K, V, R> Map<R, V> mapKeys(Map<K, V> object, Function<K, R> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<K, V> immutableObject = java.util.Collections.unmodifiableMap(object);
        return iMapMap(immutableObject, (v, i, a) -> iteratee.apply(i), MapFunction.valueIdentity());
    }
    
    /**
     * Maps a map, using a function to generate new values from the old values.
     * @param <K> The type of the keys.
     * @param <V> The type of the value in the source map.
     * @param <R> The type of the value in the destination map.
     * @param object The object to map.
     * @param iteratee The iterator to map object values to destination values.
     * @return The mapped map.
     */
    public static <K, V, R> Map<K, R> mapValues(Map<K, V> object, MapFunction<K, V, R> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<K, V> immutableObject = java.util.Collections.unmodifiableMap(object);
        return iMapMap(immutableObject, MapFunction.keyIdentity(), iteratee);
    }
    
    /**
     * Maps a map, using a function to generate new values from the old values.
     * @param <K> The type of the keys.
     * @param <V> The type of the value in the source map.
     * @param <R> The type of the value in the destination map.
     * @param object The object to map.
     * @param iteratee The iterator to map object values to destination values.
     * @return The mapped map.
     */
    public static <K, V, R> Map<K, R> mapValues(Map<K, V> object, Function<V, R> iteratee){
        Objects.requireNonNull(object);
        Objects.requireNonNull(iteratee);
        Map<K, V> immutableObject = java.util.Collections.unmodifiableMap(object);
        return iMapMap(immutableObject, MapFunction.keyIdentity(), Common.iMapFunctionFromFunction(iteratee));
    }
    
    /**
     * Internal function to copy an object, omitting key/values that predicate returns false for.
     * @param <K> The type of key.
     * @param <V> The type of value.
     * @param object The object to omit from.
     * @param predicate The predicate to determine omission.
     * @return A copy of the supplied object, with passed key/values removed.
     */
    static <K, V> Map<K, V> iOmit(Map<K, V> object, BiPredicate<K, V> predicate){
        Map<K, V> omitMap = new HashMap<>();
        for(K key : object.keySet()){
            if(!predicate.test(key, object.get(key))){
                omitMap.put(key, object.get(key));
            }
        }
        return omitMap;
    }
    
    /**
     * Return a copy of the map, with the specified keys omitted.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to omit from.
     * @param paths The keys to omit.
     * @return A copy of object, with the specified keys removed.
     */
    public static <K, V> Map<K, V> omit(Map<K, V> object, List<K> paths){
        Objects.requireNonNull(object);
        Objects.requireNonNull(paths);
        return iOmit(object, (k, v) -> paths.contains(k));
    }
    
    /**
     * Return a copy of the map, with the specified key omitted.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to omit from.
     * @param path The key to omit.
     * @return A copy of object, with the specified key removed.
     */
    public static <K, V> Map<K, V> omit(Map<K, V> object, K path){
        Objects.requireNonNull(object);
        return iOmit(object, (k, v) -> Objects.equals(k, path));
    }
    
    /**
     * Return a copy of the map, with key/values passing predicate removed.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to omit from.
     * @param predicate The predicate to determine if a key is to be kept.
     * @return A copy of the object, with passing key/values removed.
     */
    public static <K, V> Map<K, V> omitBy(Map<K, V> object, BiPredicate<K, V> predicate){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        return iOmit(object, predicate);
    }
    
    /**
     * Return a copy of the map, with keys passing predicate removed.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to omit from.
     * @param predicate The predicate to determine if a key is to be kept.
     * @return A copy of the object, with passing keys removed.
     */
    public static <K, V> Map<K, V> omitBy(Map<K, V> object, Predicate<K> predicate){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        return iOmit(object, (k, v) -> predicate.test(k));
    }
    
    /**
     * Return a copy of the map, with the specified keys only.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to pick from.
     * @param paths The keys to pick.
     * @return A copy of object, with the specified keys only.
     */
    public static <K, V> Map<K, V> pick(Map<K, V> object, List<K> paths){
        Objects.requireNonNull(object);
        Objects.requireNonNull(paths);
        return iOmit(object, (k, v) -> !paths.contains(k));
    }
    
    /**
     * Return a copy of the map, with key/values passing predicate only.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to pick from.
     * @param predicate The predicate to determine if a key is to be kept.
     * @return A copy of the object, with passing key/values only.
     */
    public static <K, V> Map<K, V> pickBy(Map<K, V> object, BiPredicate<K, V> predicate){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        return iOmit(object, predicate.negate());
    }
    
    /**
     * Return a copy of the map, with keys passing predicate only.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to pick from.
     * @param predicate The predicate to determine if a key is to be kept.
     * @return A copy of the object, with passing keys removed.
     */
    public static <K, V> Map<K, V> pickBy(Map<K, V> object, Predicate<K> predicate){
        Objects.requireNonNull(object);
        Objects.requireNonNull(predicate);
        return iOmit(object, (k, v) -> predicate.negate().test(k));
    }
    
    /**
     * Set the key-value pair in object.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to modify.
     * @param key The key to set.
     * @param value The value to set.
     * @return Object.
     */
    public static <K, V> Map<K, V> set(Map<K, V> object, K key, V value){
        Objects.requireNonNull(object);
        object.put(key, value);
        return object;
    }
    
    /**
     * Transform a map into an object by running key-value pairs through an iteratee.
     * This differs from reduction in that the MapTransformAccumulator should return
     * false if iteration should stop at any point. Accumulation is expected to
     * occur through side effects on the provided accumulator, rather than
     * potentially returning a new object each time.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param <R> The type of the transformation.
     * @param object The object to transform.
     * @param iteratee The transformation function.
     * @param accumulator The initial value of the transform.
     * @return The transform.
     */
    public static <K, V, R> R transform(Map<K, V> object, MapTransformAccumulator<K, V, R> iteratee, R accumulator){
        for(K key : object.keySet()){
            if(!iteratee.reduce(accumulator, object.get(key), key, object)){
                break;
            }
        }
        return accumulator;
    }
    
    /**
     * Remove an entry from the map.
     * @param <K> The type of the key.
     * @param object The object to remove from.
     * @param key The key to remove.
     * @return True if the map was modified, false if not.
     */
    public static <K> boolean unset(Map<K, ?> object, K key){
        Objects.requireNonNull(object);
        if(object.containsKey(key)){
            object.remove(key);
            return true;
        }
        return false;
    }
    
    /**
     * Updates the value of the specified key to a new value.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param object The object to update.
     * @param path The key to update.
     * @param updater The updater to get the new value from the old value.
     * @return Object.
     */
    public static <K, V> Map<K, V> update(Map<K, V> object, K path, UnaryOperator<V> updater){
        Objects.requireNonNull(object);
        Objects.requireNonNull(updater);
        object.put(path, updater.apply(object.get(path)));
        return object;
    }
    
    /**
     * Get a list of values of a map.
     * @param <V> The type of the values.
     * @param object The object to get the values from.
     * @return The list of values.
     */
    public static <V> List<V> values(Map<?, V> object){
        Objects.requireNonNull(object);
        return new ArrayList<>(object.values());
    }
}   
