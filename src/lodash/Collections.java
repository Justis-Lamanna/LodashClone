/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.ArrayAccumulator;
import interfaces.ArrayConsumer;
import interfaces.ArrayFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import interfaces.ArrayPredicate;
import interfaces.CollectionAccumulator;
import interfaces.CollectionFunction;
import interfaces.CollectionPredicate;
import interfaces.MapAccumulator;
import interfaces.MapConsumer;
import interfaces.MapFunction;
import interfaces.MapPredicate;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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
     * Internal function to filter lists and maps.
     * @param <V> The type the values are.
     * @param <I> The type the IDs are (index/key)
     * @param <C> The type the collection is.
     * @param collection The collection to iterate through.
     * @param getKeys The function to retrieve keys from the collection.
     * @param getValue The function to get a value from a key.
     * @param predicate The predicate to test against.
     * @return The filtered list.
     */
    static<V, I, C> List<V> iFilter(C collection, Function<C, Collection<I>> getKeys, Function<I, V> getValue, CollectionPredicate<V, I, C> predicate){
        List<V> filter = new ArrayList<>();
        for(I id : getKeys.apply(collection)){
            V value = getValue.apply(id);
            if(predicate.test(value, id, collection)){
                filter.add(value);
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
    public static <T> List<T> filter(List<T> collection, ArrayPredicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        return iFilter(immutableCollection, Common::iGetIdsFromList, collection::get, predicate);
    }
    
    /**
     * Create a list of all values from a collection that pass a predicate.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to filter.
     * @param predicate The predicate to test against.
     * @return The list of all elements from collection that passed the predicate.
     */
    public static <K, V> List<V> filter(Map<K, V> collection, MapPredicate<K, V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        return iFilter(immutableCollection, Map::keySet, collection::get, predicate);
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
    public static <K, V> List<V> filter(Map<K, V> collection, Predicate<V> predicate){
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
    static <K, V> V iFind(Map<K, V> collection, MapPredicate<K, V> predicate){
        for(K key : collection.keySet()){
            if(predicate.test(collection.get(key), key, collection)){
                return collection.get(key);
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
        if(start < 0 || start >= collection.size()){
            throw new ArrayIndexOutOfBoundsException("Start must be in bounds");
        }
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
    public static <K, V> V find(Map<K, V> collection, MapPredicate<K, V> predicate){
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
    public static <K, V> V find(Map<K, V> collection, Predicate<V> predicate){
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(Objects.requireNonNull(collection));
        MapPredicate<K, V> mapPredicate = Common.iMapPredicateFromPredicate(Objects.requireNonNull(predicate));
        return iFind(immutableCollection, mapPredicate);
    }
    
    /**
     * Find the last value that matches a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @param start The index to start searching from.
     * @return The last element in the list that matches the predicate, or null if none found.
     */
    public static <T> T findLast(List<T> collection, ArrayPredicate<T> predicate, int start){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        if(start < 0 || start >= collection.size()){
            throw new ArrayIndexOutOfBoundsException("Start must be in bounds");
        }
        return iFind(collection, predicate, start, false);
    }
    
    /**
     * Find the last value that matches a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @param start The index to start searching from.
     * @return The last element in the list that matches the predicate, or null if none found.
     */
    public static <T> T findLast(List<T> collection, Predicate<T> predicate, int start){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        if(start < 0 || start >= collection.size()){
            throw new ArrayIndexOutOfBoundsException("Start must be in bounds");
        }
        return iFind(collection, Common.iArrayPredicateFromPredicate(predicate), start, false);
    }
    
    /**
     * Find the last value that matches a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The last element in the list that matches the predicate, or null if none found.
     */
    public static <T> T findLast(List<T> collection, ArrayPredicate<T> predicate){
        return findLast(collection, predicate, collection.size() - 1);
    }
    
    /**
     * Find the last value that matches a predicate.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param predicate The predicate to determine a match.
     * @return The last element in the list that matches the predicate, or null if none found.
     */
    public static <T> T findLast(List<T> collection, Predicate<T> predicate){
        return findLast(collection, predicate, collection.size() - 1);
    }
    
    /**
     * Internal function used for forEach
     * @param <T> The type in the list.
     * @param collection The collection to iterate over
     * @param iteratee The function to call for each value.
     * @param up True if starting at the beginning, false to start at the end.
     */
    static <T> void iForEach(List<T> collection, ArrayConsumer<T> iteratee, boolean up){
        if(up){
            for(int index = 0; index < collection.size(); index++){
                iteratee.accept(collection.get(index), index, collection);
            }
        } else {
            for(int index = collection.size() - 1; index >= 0; index--){
                iteratee.accept(collection.get(index), index, collection);
            }
        }
    }
    
    /**
     * Internal function used for forEach
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param collection The collection to iterate over.
     * @param iteratee The function to call each key/value.
     */
    static <K, V> void iForEach(Map<K, V> collection, MapConsumer<K, V> iteratee){
        for(K key : collection.keySet()){
            iteratee.accept(collection.get(key), key, collection);
        }
    }
    
    /**
     * Calls a function for each member of a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <T> void forEach(List<T> collection, ArrayConsumer<T> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableList(collection), iteratee, true);
    }
    
    /**
     * Calls a function for each member of a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <T> void forEach(List<T> collection, Consumer<T> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableList(collection), Common.iArrayConsumerFromConsumer(iteratee), true);
    }
    
    /**
     * Calls a function for each member of a collection.
     * @param <K> The type the keys are.
     * @param <V> The type the values are.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <K, V> void forEach(Map<K, V> collection, MapConsumer<K, V> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableMap(collection), iteratee);
    }
    
    /**
     * Calls a function for each member of a collection.
     * @param <K> The type the keys are.
     * @param <V> The type the values are.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <K, V> void forEach(Map<K, V> collection, Consumer<V> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableMap(collection), Common.iMapConsumerFromConsumer(iteratee));
    }
    
    /**
     * Calls a function for each member of a collection, going in reverse.
     * @param <T> The type in the collection.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <T> void forEachRight(List<T> collection, ArrayConsumer<T> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableList(collection), iteratee, false);
    }
    
    /**
     * Calls a function for each member of a collection, going in reverse.
     * @param <T> The type in the collection.
     * @param collection The collection to iterate through.
     * @param iteratee The function to call for each value.
     */
    public static <T> void forEachRight(List<T> collection, Consumer<T> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        iForEach(java.util.Collections.unmodifiableList(collection), Common.iArrayConsumerFromConsumer(iteratee), false);
    }
    
    /**
     * Internal group by function.
     * @param <T> The type in the collection.
     * @param <R> The type to map into.
     * @param collection The collection to group by.
     * @param iteratee The mapping function.
     * @return A grouped mapping.
     */
    static <T, R> Map<R, List<T>> iGroupBy(Collection<T> collection, Function<T, R> iteratee){
        Map<R, List<T>> groupMap = new HashMap<>();
        for(T value : collection){
            R mappedValue = iteratee.apply(value);
            if(groupMap.containsKey(mappedValue)){
                groupMap.get(mappedValue).add(value);
            } else {
                List<T> group = new ArrayList<>();
                group.add(value);
                groupMap.put(mappedValue, group);
            }
        }
        return groupMap;
    }
    
    /**
     * Creates a grouping of a list.
     * Creates a map composed of keys generated from the results of running each
     * element of collection through iteratee, and values being the list of values
     * that mapped to that key.
     * @param <T> The type contained in the list.
     * @param <R> The type the values are mapped into.
     * @param collection The collection to iterate through.
     * @param iteratee The function to map each value.
     * @return An object containing mapped values, and the list of values that mapped to them.
     */
    public static <T, R> Map<R, List<T>> groupBy(List<T> collection, Function<T, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iGroupBy(collection, iteratee);
    }
    
    /**
     * Creates a grouping of a list.
     * Creates a map composed of keys generated from the results of running each
     * element of collection through iteratee, and values being the list of values
     * that mapped to that key.
     * @param <K> The type of the keys in the map.
     * @param <V> The type of the values in the map.
     * @param <R> The type the values are mapped into.
     * @param collection The collection to iterate through.
     * @param iteratee The function to map each value.
     * @return An object containing mapped values, and the list of values that mapped to them.
     */
    public static <K, V, R> Map<R, List<V>> groupBy(Map<K, V> collection, Function<V, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iGroupBy(collection.values(), iteratee);
    }
    
    /**
     * Internal function to test if a value is in a list.
     * @param <T> The type in the list.
     * @param collection The collection to search.
     * @param item The item to search for.
     * @param start The index to begin searching at.
     * @return True if the item is contained in the list.
     */
    static <T> boolean iIncludes(List<T> collection, T item, int start){
        for(int index = start; index < collection.size(); index++){
            if(Objects.equals(item, collection.get(index))){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Internal function to test if a value is in a list.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to search.
     * @param item The item to search for.
     * @param start The index to begin searching at.
     * @return True if the item is contained in the list.
     */
    static <K, V> boolean iIncludes(Map<K, V> collection, V item){
        for(K key : collection.keySet()){
            if(Objects.equals(collection.get(key), item)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Internal function to test if a String is contained in a String.
     * @param collection The string to search.
     * @param item The substring to search for.
     * @param start The index to begin searching at.
     * @return True if the item is contained in the collection.
     */
    static boolean iIncludes(String collection, String item, int start){
        return collection.substring(start).contains(item);
    }
    
    /**
     * Check if a value is contained in a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param value The value to search for.
     * @param start The index to start searching.
     * @return True if the object was found.
     */
    public static <T> boolean includes(List<T> collection, T value, int start){
        Objects.requireNonNull(collection);
        if(start < 0 || start >= collection.size()){
            throw new ArrayIndexOutOfBoundsException("Start not in bounds.");
        }
        return iIncludes(collection, value, start);
    }
    
    /**
     * Check if a substring is contained in a string.
     * @param string The string to search.
     * @param substring The string to search for.
     * @param start The index to begin searching at.
     * @return True if the substring is contained in the string.
     */
    public static boolean includes(String string, String substring, int start){
        Objects.requireNonNull(string);
        Objects.requireNonNull(substring);
        if(start < 0 || start >= string.length()){
            throw new ArrayIndexOutOfBoundsException("Start not in bounds.");
        }
        return iIncludes(string, substring, start);
    }
    
    /**
     * Check if a value is contained in a collection.
     * @param <T> The type in the collection.
     * @param collection The collection to search.
     * @param value The value to search for.
     * @return True if the value was found.
     */
    public static <T> boolean includes(List<T> collection, T value){
        Objects.requireNonNull(collection);
        return iIncludes(collection, value, 0);
    }
    
    /**
     * Check if a value is a value in the map.
     * @param <K> The type of the keys in the collection.
     * @param <V> The type of the values in the collection.
     * @param collection The collection to search.
     * @param value The value to search for.
     * @return True if the value was found.
     */
    public static <K, V> boolean includes(Map<K, V> collection, V value){
        Objects.requireNonNull(collection);
        return iIncludes(collection, value);
    }
    
    /**
     * Check if a substring is contained in a string.
     * @param string The string to search.
     * @param substring The string to search for.
     * @return True if the substring is contained in the string.
     */
    public static boolean includes(String string, String substring){
        Objects.requireNonNull(string);
        Objects.requireNonNull(substring);
        return iIncludes(string, substring, 0);
    }
    
    /**
     * Internal function for keyBy
     * @param <T> The type in the collection.
     * @param <R> The type being mapped to, and the type of the key.
     * @param collection The collection to iterate over.
     * @param iteratee The function to generate keys for each value in collection.
     * @return A map, with keys composed of running each value through an iteratee.
     */
    static <T, R> Map<R, T> iKeyBy(Collection<T> collection, Function<T, R> iteratee){
        Map<R, T> keyBy = new HashMap<>();
        for(T value : collection){
            R key = iteratee.apply(value);
            keyBy.put(key, value);
        }
        return keyBy;
    }
    
    /**
     * Creates an object composed of keys generated by running values through an iteratee.
     * @param <T> The type in the collection.
     * @param <R> The type of the keys to generate.
     * @param collection The collection to iterate over.
     * @param iteratee The function to generate the keys.
     * @return The generated map.
     */
    public static <T, R> Map<R, T> keyBy(List<T> collection, Function<T, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iKeyBy(collection, iteratee);
    }
    
    /**
     * Creates an object composed of keys generated by running values through an iteratee.
     * @param <K> The type of the key in the collection.
     * @param <V> The type of the values in the collection.
     * @param <R> The type of the keys to generate.
     * @param collection The collection to iterate over.
     * @param iteratee The function to generate the keys.
     * @return The generated map.
     */
    public static <K, V, R> Map<R, V> keyBy(Map<K, V> collection, Function<V, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iKeyBy(collection.values(), iteratee);
    }
    
    /**
     * Internal function to perform a map.
     * @param <K> The type the keys of the map are.
     * @param <V> The type the values of the map are.
     * @param <C> The type the collection is.
     * @param <R> The type the mapping function returns.
     * @param collection The collection to iterate over.
     * @param getKeys The function to retrieve keys from the collection.
     * @param getValue The function to retrieve a value from a key.
     * @param iteratee The function to call on each iteration.
     * @return The mapped array.
     */
    static <K, V, C, R> List<R> iMap(C collection, Function<C, Collection<K>> getKeys, Function<K, V> getValue, CollectionFunction<V, K, C, R> iteratee){
        List<R> map = new ArrayList<>();
        for(K key : getKeys.apply(collection)){
            map.add(iteratee.map(getValue.apply(key), key, collection));
        }
        return map;
    }
    
    /**
     * Create an array of values by running each element through a mapping function.
     * @param <T> The type of the collection.
     * @param <R> The type to convert to.
     * @param collection The collection to iterate through.
     * @param iteratee The function to cause the mapping.
     * @return The mapped list.
     */
    public static <T, R> List<R> map(List<T> collection, ArrayFunction<T, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iMap(collection, Common::iGetIdsFromList, collection::get, iteratee);
    }
    
    /**
     * Create an array of values by running each element through a mapping function.
     * @param <T> The type of the collection.
     * @param <R> The type to convert to.
     * @param collection The collection to iterate through.
     * @param iteratee The function to cause the mapping.
     * @return The mapped list.
     */
    public static <T, R> List<R> map(List<T> collection, Function<T, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iMap(collection, Common::iGetIdsFromList, collection::get, Common.iArrayFunctionFromFunction(iteratee));
    }
    
    /**
     * Create an array of values by running each element through a mapping function.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param <R> The type to convert to.
     * @param collection The collection to iterate through.
     * @param iteratee The function to cause the mapping.
     * @return The mapped list.
     */
    public static <K, V, R> List<R> map(Map<K, V> collection, MapFunction<K, V, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        return iMap(collection, Map::keySet, collection::get, iteratee);
    }
    
    /**
     * Create an array of values by running each element through a mapping function.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param <R> The type to convert to.
     * @param collection The collection to iterate through.
     * @param iteratee The function to cause the mapping.
     * @return The mapped list.
     */
    public static <K, V, R> List<R> map(Map<K, V> collection, Function<V, R> iteratee){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(iteratee);
        MapFunction<K, V, R> func = Common.iMapFunctionFromFunction(iteratee);
        return iMap(collection, Map::keySet, collection::get, func);
    }
    
    /**
     * Internal function to partition 
     * @param <V> The type in the collection.
     * @param values The values to iterate over.
     * @param predicate The predicate to use.
     * @return An list of lists, the first list all the true values, the second all the false ones.
     */
    static <V> List<List<V>> iPartition(Collection<V> values, Predicate<V> predicate){
        List<V> trueList = new ArrayList<>();
        List<V> falseList = new ArrayList<>();
        for(V value : values){
            if(predicate.test(value)){
                trueList.add(value);
            } else {
                falseList.add(value);
            }
        }
        return java.util.Arrays.asList(trueList, falseList);
    }
    
    /**
     * Partitions a list based on a predicate.
     * The returned list contains two lists, the first being all the values
     * the predicate returned true for, and the second being all the values
     * the predicate returned false for.
     * @param <T> The type in the collection.
     * @param collection The collection to iterate over.
     * @param predicate The predicate to use.
     * @return A list, containing a true list and a false list.
     */
    public static <T> List<List<T>> partition(List<T> collection, Predicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        return iPartition(collection, predicate);
    }
    
    /**
     * Partitions a list based on a predicate.
     * The returned list contains two lists, the first being all the values
     * the predicate returned true for, and the second being all the values
     * the predicate returned false for.
     * @param <K> The type the keys are.
     * @param <V> The type the values are.
     * @param collection The collection to iterate over.
     * @param predicate The predicate to use.
     * @return A list, containing a true list and a false list.
     */
    public static <K, V> List<List<V>> partition(Map<K, V> collection, Predicate<V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        return iPartition(collection.values(), predicate);
    }
    
    /**
     * Internal function to reduce a collection.
     * @param <V> The type the values are.
     * @param <I> The type the ids are.
     * @param <C> The type the collection is.
     * @param <R> The type the reduction is.
     * @param collection The collection to iterate over.
     * @param getKeys The function to get keys from the collection.
     * @param getValue The function to get a value from a key.
     * @param accumulator The accumulator to use.
     * @return The accumulated value.
     */
    static <V, I, C, R> R iReduce(C collection, Function<C, Collection<I>> getKeys, Function<I, V> getValue, CollectionAccumulator<V, I, C, R> accumulator, R initial){
        R accumulated = initial;
        for(I id : getKeys.apply(collection)){
            V value = getValue.apply(id);
            accumulated = accumulator.reduce(accumulated, value, id, collection);
        }
        return accumulated;
    }
    
    /**
     * Reduce a collection to a single value.
     * @param <T> The type in the list.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value.
     * @return The reduced list.
     */
    public static <T, R> R reduce(List<T> collection, ArrayAccumulator<T, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        return iReduce(immutableCollection, Common::iGetIdsFromList, collection::get, accumulator, initial);
    }
    
    /**
     * Reduce a collection to a single value.
     * @param <T> The type in the list.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value.
     * @return The reduced list.
     */
    public static <T, R> R reduce(List<T> collection, BiFunction<R, T, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        ArrayAccumulator<T, R> arrayAccumulator = Common.iArrayAccumulatorFromBiFunction(accumulator);
        return iReduce(immutableCollection, Common::iGetIdsFromList, collection::get, arrayAccumulator, initial);
    }
    
    /**
     * Reduce a collection to a single value.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value.
     * @return The reduced list.
     */
    public static <K, V, R> R reduce(Map<K, V> collection, MapAccumulator<K, V, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        return iReduce(immutableCollection, Map::keySet, collection::get, accumulator, initial);
    }
    
    /**
     * Reduce a collection to a single value.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value.
     * @return The reduced list.
     */
    public static <K, V, R> R reduce(Map<K, V> collection, BiFunction<R, V, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        MapAccumulator<K, V, R> mapAccumulator = Common.iMapAccumulatorFromBiFunction(accumulator);
        return iReduce(immutableCollection, Map::keySet, collection::get, mapAccumulator, initial);
    }
    
    /**
     * Reduce a collection to a single value, starting from the right.
     * @param <T> The type of the list.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value of the reduction.
     * @return The reduced list.
     */
    public static <T, R> R reduceRight(List<T> collection, ArrayAccumulator<T, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        List<T> immutableCollection  = java.util.Collections.unmodifiableList(Arrays.reverse(collection));
        return iReduce(immutableCollection, Common::iGetIdsFromList, collection::get, accumulator, initial);
    }
    
    /**
     * Reduce a collection to a single value, starting from the right.
     * @param <T> The type of the list.
     * @param <R> The type of the reduction.
     * @param collection The collection to reduce.
     * @param accumulator The accumulator to use.
     * @param initial The initial value of the reduction.
     * @return The reduced list.
     */
    public static <T, R> R reduceRight(List<T> collection, BiFunction<R, T, R> accumulator, R initial){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(accumulator);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(Arrays.reverse(collection));
        ArrayAccumulator<T, R> arrayAccumulator = Common.iArrayAccumulatorFromBiFunction(accumulator);
        return iReduce(immutableCollection, Common::iGetIdsFromList, collection::get, arrayAccumulator, initial);
    }
    
    /**
     * Internal function for reject.
     * @param <V> The type of the values.
     * @param <I> The type of the identifiers.
     * @param <C> The type of the collection.
     * @param collection The collection to reject from.
     * @param getKeys The function to get the keys of a collection.
     * @param getValue The function to get the value from a key.
     * @param predicate The predicate to determine rejection.
     * @return The list of values, with rejections removed.
     */
    static <V, I, C> List<V> iReject(C collection, Function<C, Collection<I>> getKeys, Function<I, V> getValue, CollectionPredicate<V, I, C> predicate){
        List<V> reject = new ArrayList<>();
        for(I id : getKeys.apply(collection)){
            V value = getValue.apply(id);
            if(!predicate.test(value, id, collection)){
                reject.add(value);
            }
        }
        return reject;
    }
    
    /**
     * Returns a collection with all values that failed a predicate.
     * This is the opposite of filter().
     * @param <T> The type in the collection.
     * @param collection The collection to reject from.
     * @param predicate The predicate to determine rejection.
     * @return A list of values that failed the predicate.
     */
    public static <T> List<T> reject(List<T> collection, ArrayPredicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        return iReject(immutableCollection, Common::iGetIdsFromList, collection::get, predicate);
    }
    
    /**
     * Returns a collection with all values that failed a predicate.
     * This is the opposite of filter().
     * @param <T> The type in the collection.
     * @param collection The collection to reject from.
     * @param predicate The predicate to determine rejection.
     * @return A list of values that failed the predicate.
     */
    public static <T> List<T> reject(List<T> collection, Predicate<T> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        List<T> immutableCollection = java.util.Collections.unmodifiableList(collection);
        ArrayPredicate<T> arrayPredicate = Common.iArrayPredicateFromPredicate(predicate);
        return iReject(immutableCollection, Common::iGetIdsFromList, collection::get, arrayPredicate);
    }
    
    /**
     * Returns a collection with all values that failed a predicate.
     * This is the opposite of filter().
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param collection The collection to reject from.
     * @param predicate The predicate to determine rejection.
     * @return A list of values that failed the predicate.
     */
    public static <K, V> List<V> reject(Map<K, V> collection, MapPredicate<K, V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        return iReject(immutableCollection, Map::keySet, collection::get, predicate);
    }
    
    /**
     * Returns a collection with all values that failed a predicate.
     * This is the opposite of filter().
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param collection The collection to reject from.
     * @param predicate The predicate to determine rejection.
     * @return A list of values that failed the predicate.
     */
    public static <K, V> List<V> reject(Map<K, V> collection, Predicate<V> predicate){
        Objects.requireNonNull(collection);
        Objects.requireNonNull(predicate);
        Map<K, V> immutableCollection = java.util.Collections.unmodifiableMap(collection);
        MapPredicate<K, V> arrayPredicate = Common.iMapPredicateFromPredicate(predicate);
        return iReject(immutableCollection, Map::keySet, collection::get, arrayPredicate);
    }
    
    /**
     * Internal function to get a sample of elements.
     * @param <T> The type in the collection.
     * @param collection The collection to choose from.
     * @param number The number of elements to choose.
     * @return A list of chosen elements.
     */
    static <T> List<T> iSample(List<T> collection, int number){
        List<T> sample = new ArrayList<>();
        Random rnd = new Random();
        if(collection.isEmpty()){
            return java.util.Arrays.asList((T) null);
        }
        for(int index = 0; index < number; index++){
            int randomIndex = rnd.nextInt(collection.size());
            sample.add(collection.get(randomIndex));
        }
        return collection;
    }
    
    /**
     * Return a random entry in the list.
     * @param <T> The type in the collection.
     * @param collection The collection to choose from.
     * @return A random value in the collection.
     */
    public static <T> T sample(List<T> collection){
        Objects.requireNonNull(collection);
        return iSample(collection, 1).get(0);
    }
    
    /**
     * Return a random value in the map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param collection The collection to choose from.
     * @return A random value in the collection.
     */
    public static <K, V> V sample(Map<K, V> collection){
        Objects.requireNonNull(collection);
        return iSample(new ArrayList<>(collection.values()), 1).get(0);
    }
    
    /**
     * Return some amount of values in the list.
     * @param <T> The type in the collection.
     * @param collection The collection to choose from.
     * @param number The number of random elements to choose.
     * @return A list of randomly-chosen values.
     */
    public static <T> List<T> sampleSize(List<T> collection, int number){
        Objects.requireNonNull(collection);
        if(number < 0){
            throw new IllegalArgumentException("Size must be non-negative");
        }
        return iSample(collection, number);
    }
    
    /**
     * Return some amount of values in the list.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param collection The collection to choose from.
     * @param number The number of random elements to choose.
     * @return A list of randomly-chosen values.
     */
    public static <K, V> List<V> sampleSize(Map<K, V> collection, int number){
        Objects.requireNonNull(collection);
        if(number < 0){
            throw new IllegalArgumentException("Size must be non-negative");
        }
        return iSample(new ArrayList<>(collection.values()), number);
    }
}
