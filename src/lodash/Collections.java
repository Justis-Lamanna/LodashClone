/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import module.ArrayPredicate;
import module.CollectionPredicate;
import module.MapPredicate;

/**
 *
 * @author Justis
 */
public class Collections {
    
    private Collections(){
        throw new IllegalStateException("Cannot instantiate Collections");
    }
    
    /**
     * Internal function to perform countBy
     * @param <V> The type of the value.
     * @param <R> The type the value is converted to before counting.
     * @param collection The collection to iterate through.
     * @param iteratee The function to map the value before counting.
     * @return A list of values and their counts.
     */
    static <V, R> Map<R, Integer> iCountBy(Collection<V> collection, Function<V, R> iteratee){
        Map<R, Integer> counts = new HashMap<>();
        for(V value : collection){
            R mappedValue = iteratee.apply(value);
            counts.put(mappedValue, counts.getOrDefault(mappedValue, 0) + 1);
        }
        return counts;
    }
    
    /**
     * Creates an object, composed of transformations and the number of times they occurred.
     * The map is created by applying the iteratee to each value in the collection.
     * The key becomes the transformed value, and the value is the number of times it
     * occurs.
     * @param <T> The type in the array.
     * @param <R> The type converted to.
     * @param collection The collection to count.
     * @param iteratee The mapping function called before counting.
     * @return A list of mapped values and the number of times they occurred.
     */
    public static <T, R> Map<R, Integer> countBy(List<T> collection, Function<T, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iCountBy(collection, iteratee);
    }
    
    /**
     * Creates an object, composed of transformations and the number of times they occurred.
     * The map is created by applying the iteratee to each value in the collection.
     * The key becomes the transformed value, and the value is the number of times it
     * occurs.
     * @param <K> The type of the map's key.
     * @param <V> The type of the map's value.
     * @param <R> The type converted to.
     * @param collection The collection to count.
     * @param iteratee The mapping function called before counting.
     * @return A list of mapped values and the number of times they occurred.
     */
    public static <K, V, R> Map<R, Integer> countBy(Map<K, V> collection, Function<V, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iCountBy(collection.values(), iteratee);
    }
    
    /**
     * Internal function for every.
     * @param <V> The type of the values.
     * @param <I> The type of the identifiers (indexes/keys).
     * @param <C> The type of the collection.
     * @param collection The collection to iterate through.
     * @param ids The list of identifiers.
     * @param getValues The function to retrieve values from the identifiers.
     * @param predicate The function to test if a value passes or fails.
     * @return True if everything passes, false if something fails.
     */
    static <V, I, C> boolean iEvery(C collection, Collection<I> ids, Function<I, V> getValues, CollectionPredicate<V, I, C> predicate){
        for(I id : ids){
            if(!predicate.test(getValues.apply(id), id, collection)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if a predicate is true for all members of a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to check.
     * @param predicate The predicate to check if a value is true.
     * @return True if the predicate was true for every member of the collection.
     */
    public static <T> boolean every(List<T> collection, ArrayPredicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        return iEvery(immutableCollection, Common.iGetIdsFromList(collection), collection::get, predicate);
    }
    
    /**
     * Check if a predicate is true for all members of a collection.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to check.
     * @param predicate The predicate to check if a value is true.
     * @return True if the predicate was true for every member of the collection.
     */
    public static <K, V> boolean every(Map<K, V> collection, MapPredicate<K, V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        return iEvery(immutableCollection, immutableCollection.keySet(), collection::get, predicate);
    }
    
    /**
     * Check if a predicate is true for all members of a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to check.
     * @param predicate The predicate to check if a value is true.
     * @return True if the predicate was true for every member of the collection.
     */
    public static <T> boolean every(List<T> collection, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return every(collection, Common.iArrayPredicateFromPredicate(predicate));
    }
    
    /**
     * Check if a predicate is true for all members of a collection.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to check.
     * @param predicate The predicate to check if a value is true.
     * @return True if the predicate was true for every member of the collection.
     */
    public static <K, V> boolean every(Map<K, V> collection, Predicate<V> predicate){
        Objects.requireNonNull(predicate);
        return every(collection, Common.iMapPredicateFromPredicate(predicate));
    }
    
    /**
     * Create a list of all values from a collection that pass a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to filter.
     * @param predicate The predicate to test against.
     * @return The list of all elements from collection that passed the predicate.
     */
    public static <T> List<T> filter(List<T> collection, ArrayPredicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        List<T> filter = new ArrayList<>();
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        for(int index = 0; index < collection.size(); index++){
            if(predicate.test(collection.get(index), index, immutableCollection)){
                filter.add(collection.get(index));
            }
        }
        return filter;
    }
    
    /**
     * Create a list of all values from a collection that pass a predicate.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to filter.
     * @param predicate The predicate to test against.
     * @return The list of all elements from collection that passed the predicate.
     */
    public static <K, V> Map<K, V> filter(Map<K, V> collection, MapPredicate<K, V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        Map<K, V> filter = new HashMap<>();
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        for(K key : collection.keySet()){
            if(predicate.test(collection.get(key), key, immutableCollection)){
                filter.put(key, collection.get(key));
            }
        }
        return filter;
    }
    
    /**
     * Create a list of all values from a collection that pass a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to filter.
     * @param predicate The predicate to test against.
     * @return The list of all elements from collection that passed the predicate.
     */
    public static <T> List<T> filter(List<T> collection, Predicate<T> predicate){
        Objects.requireNonNull(predicate);
        return filter(collection, Common.iArrayPredicateFromPredicate(predicate));
    }
    
    /**
     * Create a list of all values from a collection that pass a predicate.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to filter.
     * @param predicate The predicate to test against.
     * @return The list of all elements from collection that passed the predicate.
     */
    public static <K, V> Map<K, V> filter(Map<K, V> collection, Predicate<V> predicate){
        Objects.requireNonNull(predicate);
        return filter(collection, Common.iMapPredicateFromPredicate(predicate));
    }
    
    /**
     * Internal function to find a value in a list.
     * This only works for list, because using for maps doesn't really make sense.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @param fromIndex The starting index.
     * @param up True if the search should start from beginning, false for end.
     * @return The found element, or null if none found.
     */
    static <T> T iFind(List<T> collection, ArrayPredicate<T> predicate, int fromIndex, boolean up){
        if(up){
            for(int index = fromIndex; index < collection.size(); index++){
                if(predicate.test(collection.get(index), index, collection)){
                    return collection.get(index);
                }
            }
        } else {
            for(int index = fromIndex; index >= 0; index--){
                if(predicate.test(collection.get(index), index, collection)){
                    return collection.get(index);
                }
            }
        }
        return null;
    }
    
    /**
     * Internal function to find a value in a map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The found key, or null.
     */
    static <K, V> K iFind(Map<K, V> collection, MapPredicate<K, V> predicate){
        for(K key : collection.keySet()){
            if(predicate.test(collection.get(key), key, collection)){
                return key;
            }
        }
        return null;
    }
    
    /**
     * Find a value that matches a predicate.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @param start The starting index to search.
     * @return The found item, or null if none was found.
     */
    public static <T> T find(List<T> collection, ArrayPredicate<T> predicate, int start){
        List<T> immutableCollection = java.util.Collections.unmodifiableList(Objects.requireNonNull(collection));
        return iFind(immutableCollection, Objects.requireNonNull(predicate), start, true);
    }
    
    /**
     * Find a value that matches a predicate.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @param start The starting index to search.
     * @return The found item, or null if none was found.
     */
    public static <T> T find(List<T> collection, Predicate<T> predicate, int start){
        List<T> immutableCollection = java.util.Collections.unmodifiableList(Objects.requireNonNull(collection));
        ArrayPredicate<T> arrayPredicate = Common.iArrayPredicateFromPredicate(Objects.requireNonNull(predicate));
        return iFind(immutableCollection, arrayPredicate, start, true);
    }
    
    /**
     * Find a value that matches a predicate.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The found item, or null if none was found.
     */
    public static <T> T find(List<T> collection, ArrayPredicate<T> predicate){
        List<T> immutableCollection = java.util.Collections.unmodifiableList(Objects.requireNonNull(collection));
        return iFind(immutableCollection, Objects.requireNonNull(predicate), 0, true);
    }
    
    /**
     * Find a key whose value matches a predicate.
     * @param <K> The type of keys in the collection.
     * @param <V> The type of values in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The found item, or null if none was found.
     */
    public static <K, V> K find(Map<K, V> collection, MapPredicate<K, V> predicate){
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(Objects.requireNonNull(collection));
        return iFind(immutableCollection, Objects.requireNonNull(predicate));
    }
    
    /**
     * Find a value that matches a predicate.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The found item, or null if none was found.
     */
    public static <T> T find(List<T> collection, Predicate<T> predicate){
        List<T> immutableCollection = java.util.Collections.unmodifiableList(Objects.requireNonNull(collection));
        ArrayPredicate<T> arrayPredicate = Common.iArrayPredicateFromPredicate(Objects.requireNonNull(predicate));
        return iFind(immutableCollection, arrayPredicate, 0, true);
    }
    
    /**
     * Find a key whose value matches a predicate.
     * @param <K> The type of keys in the collection.
     * @param <V> The type of values in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The found item, or null if none was found.
     */
    public static <K, V> K find(Map<K, V> collection, Predicate<V> predicate){
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(Objects.requireNonNull(collection));
        MapPredicate<K, V> mapPredicate = Common.iMapPredicateFromPredicate(Objects.requireNonNull(predicate));
        return iFind(immutableCollection, mapPredicate);
    }
}
