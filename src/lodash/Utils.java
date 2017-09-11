/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import utils.Pair;

/**
 * Utility methods
 * @author Justis
 */
public class Utils {
    
    private Utils(){
        throw new IllegalStateException("Cannot instantiate Utils");
    }
    
    /**
     * Creates a function that returns a suppliers value if the corresponding predicate is true.
     * When the function is invoked, each pair is iterated through. If a pair's
     * predicate is true, the pair's supplier is invoked and returned. Null is returned
     * if no predicates pass.
     * @param <T> The type of the input.
     * @param <R> The type of the output.
     * @param pairs The pairs of Predicates and Suppliers.
     * @return The function created.
     */
    public static <T, R> Function<T, R> cond(List<Pair<Predicate<T>, Supplier<R>>> pairs){
        Objects.requireNonNull(pairs);
        return (T t) -> {
            for(Pair<Predicate<T>, Supplier<R>> pair : pairs){
                if(pair.first().test(t)){
                    return pair.last().get();
                }
            }
            return null;
        };
    }
    
    /**
     * Returns a function that tests if a map conforms to a set of predicates.
     * When the returned function is invoked, each key in source is iterated
     * through. The corresponding predicate in source is invoked with the corresponding
     * value in the invoked map. If all predicates pass, true is returned.
     * 
     * Put another way, for each (K)ey/(P)redicate in source, and each (K)ey/(V)value
     * in the map provided to the predicate, P.test(V) must return true.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param source The source of predicates.
     * @return The predicate created.
     */
    public static <K, V> Predicate<Map<K, V>> conforms(Map<K, Predicate<V>> source){
        Objects.requireNonNull(source);
        return (Map<K, V> t) -> {
            return source.keySet().stream()
                    .allMatch((key) -> (source.get(key).test(t.get(key))));
        };
    }
    
    /**
     * Creates a function that always returns value.
     * @param <T> The type to return.
     * @param value The value to return.
     * @return A function which always returns a value.
     */
    public static <T> Supplier<T> constant(T value){
        return () -> value;
    }
    
    /**
     * Returns value, or defaultValue if value was null.
     * @param <T> The type of the values.
     * @param value The value.
     * @param defaultValue The default, if value is null.
     * @return Value, or defaultValue.
     */
    public static <T> T defaultTo(T value, T defaultValue){
        return value == null ? defaultValue : value;
    }
    
    /**
     * Returns the provided value.
     * @param <T> The type of the values.
     * @param value The value to return.
     * @return Value.
     */
    public static <T> T identity(T value){
        return value;
    }
    
    /**
     * Creates a predicate that tests if the supplied value equals the source.
     * @param <T> The type the value is.
     * @param source The left side of the equality check.
     * @return A predicate which tests if the supplied value equals source.
     */
    public static <T> Predicate<T> matches(T source){
        return t -> Objects.equals(source, t);
    }
    
    /**
     * Create a predicate that tests if a map's property matches a source value.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param key The key to check.
     * @param srcValue The source value to compare against.
     * @return A function which tests is the supplied map's key/value property matches key/srcValue.
     */
    public static <K, V> Predicate<Map<K, V>> matchesProperty(K key, V srcValue){
        return obj -> Objects.equals(obj.get(key), srcValue);
    }
    
    /**
     * Returns null.
     * @return Null.
     */
    public static Object noop(){
        return null;
    }
    
    /**
     * Creates a function which returns the value of key in the provided object.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param key The key to retrieve.
     * @return A function that returns the value from the object.
     */
    public static <K, V> Function<Map<K, V>, V> property(K key){
        return map -> map.get(key);
    }
    
    /**
     * Creates a function which returns the value of the provided key in map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param map The map to retrieve from.
     * @return A function that returns the value from the object.
     */
    public static <K, V> Function<K, V> propertyOf(Map<K, V> map){
        return key -> map.get(key);
    }
    
    /**
     * Creates a list of integers progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @param step The step size to take.
     * @return The range created.
     */
    public static List<Integer> range(int start, int end, int step){
        if(start < end && step < 0){
            throw new IndexOutOfBoundsException("Increasing range, but decreasing step");
        } else if(start > end && step > 0){
            throw new IndexOutOfBoundsException("Decreasing range, but increasing step");
        }
        List<Integer> range = new ArrayList<>();
        if(step < 0){
            for(int i = start; i > end; i += step){
                range.add(i);
            }
        } else if(step > 0) {
            for(int i = start; i < end; i += step){
                range.add(i);
            }
        } else {
            for(int i = start; i < end; i++){
                range.add(start);
            }
        }
        return range;
    }
    
    /**
     * Creates a list of integers progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Integer> range(int start, int end){
        return range(start, end, start < end ? 1 : -1);
    }
    
    /**
     * Creates a list of integers from 0 up to, but not including, end.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Integer> range(int end){
        return range(0, end, 0 < end ? 1 : -1);
    }
    
    /**
     * Creates a list of doubles progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @param step The step size to take.
     * @return The range created.
     */
    public static List<Double> range(double start, double end, double step){
        if(!Double.isFinite(start) || !Double.isFinite(end) || !Double.isFinite(step)){
            throw new IllegalArgumentException("Non-finite value specified");
        } else if(start < end && step < 0){
            throw new IndexOutOfBoundsException("Increasing range, but decreasing step");
        } else if(start > end && step > 0){
            throw new IndexOutOfBoundsException("Decreasing range, but increasing step");
        }
        List<Double> range = new ArrayList<>();
        if(step < 0){
            for(double i = start; i > end; i += step){
                range.add(i);
            }
        } else if(step > 0) {
            for(double i = start; i < end; i += step){
                range.add(i);
            }
        } else {
            for(double i = start; i < end; i++){
                range.add(start);
            }
        }
        return range;
    }
    
    /**
     * Creates a list of doubles progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Double> range(double start, double end){
        return range(start, end, 1);
    }
    
    /**
     * Creates a list of doubles from 0 up to, but not including, end.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Double> range(double end){
        return range(0, end, 0 < end ? 1 : -1);
    }
    
    /**
     * Creates a list of integers progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @param step The step size to take.
     * @return The range created.
     */
    public static List<Integer> rangeRight(int start, int end, int step){
        if(start < end && step < 0){
            throw new IndexOutOfBoundsException("Increasing range, but decreasing step");
        } else if(start > end && step > 0){
            throw new IndexOutOfBoundsException("Decreasing range, but increasing step");
        }
        List<Integer> range = new ArrayList<>();
        if(step < 0){
            for(int i = start; i > end; i += step){
                range.add(0, i);
            }
        } else if(step > 0){
            for(int i = start; i < end; i += step){
                range.add(0, i);
            }
        } else {
            for(int i = start; i < end; i++){
                range.add(start);
            }
        }
        return range;
    }
    
    /**
     * Creates a list of integers progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Integer> rangeRight(int start, int end){
        return rangeRight(start, end, start < end ? 1 : -1);
    }
    
    /**
     * Creates a list of integers from 0 up to, but not including, end.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Integer> rangeRight(int end){
        return rangeRight(0, end, 0 < end ? 1 : -1);
    }
    
    /**
     * Creates a list of doubles progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @param step The step size to take.
     * @return The range created.
     */
    public static List<Double> rangeRight(double start, double end, double step){
        if(!Double.isFinite(start) || !Double.isFinite(end) || !Double.isFinite(step)){
            throw new IllegalArgumentException("Non-finite value specified");
        } else if(start < end && step < 0){
            throw new IndexOutOfBoundsException("Increasing range, but decreasing step");
        } else if(start > end && step > 0){
            throw new IndexOutOfBoundsException("Decreasing range, but increasing step");
        }
        List<Double> range = new ArrayList<>();
        if(step < 0){
            for(double i = start; i > end; i += step){
                range.add(0, i);
            }
        } else if(step > 0) {
            for(double i = start; i < end; i += step){
                range.add(0, i);
            }
        } else {
            for(double i = start; i < end; i++){
                range.add(start);
            }
        }
        return range;
    }
    
    /**
     * Creates a list of doubles progressing from start up to, but not including, end.
     * @param start The value to start at.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Double> rangeRight(double start, double end){
        return range(start, end, 1);
    }
    
    /**
     * Creates a list of doubles from 0 up to, but not including, end.
     * @param end The value to end at.
     * @return The range created.
     */
    public static List<Double> rangeRight(double end){
        return range(0, end, 0 < end ? 1 : -1);
    }
    
    /**
     * Returns an empty list.
     * @param <T> The type in the list.
     * @return An empty list.
     */
    public static <T> List<T> stubList(){
        return java.util.Collections.emptyList();
    }
    
    /**
     * Returns false.
     * @return False.
     */
    public static boolean stubFalse(){
        return false;
    }
    
    /**
     * Returns an empty map.
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @return An empty map.
     */
    public static <K, V> Map<K, V> stubMap(){
        return java.util.Collections.emptyMap();
    }
    
    /**
     * Returns an empty string.
     * @return An empty string.
     */
    public static String stubString(){
        return "";
    }
    
    /**
     * Returns true.
     * @return True.
     */
    public static boolean stubTrue(){
        return true;
    }
    
    /**
     * Invokes a function a number times, keeping a list of all function returns. 
     * @param <T> The type returned by the function.
     * @param n The number of times to invoke.
     * @param iteratee The function called each iteration.
     * @return A list of returned values.
     */
    public static <T> List<T> times(int n, Function<Integer, T> iteratee){
        List<T> list = new ArrayList<>();
        for(int index = 0; index < n; index++){
            list.add(iteratee.apply(index));
        }
        return list;
    }
    
    //The counter for unique IDs.
    private static BigInteger uniqueId = BigInteger.ZERO;
    
    /**
     * Create a unique ID.
     * @param prefix The prefix to prepend to the unique ID.
     * @return The unique ID.
     */
    public static String uniqueId(String prefix){
        String id = prefix + uniqueId.toString();
        uniqueId = uniqueId.add(BigInteger.ONE);
        return prefix + id;
    }
    
    /**
     * Create a unique ID.
     * @return The unique ID.
     */
    public static String uniqueId(){
        return uniqueId("");
    }
}
