/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.ArrayConsumer;
import interfaces.ArrayFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import interfaces.ArrayPredicate;
import interfaces.MapConsumer;
import interfaces.MapFunction;
import interfaces.MapPredicate;
import java.util.function.Consumer;

/**
 *
 * @author Justis
 */
public class Common {
    
    private Common(){
        throw new IllegalStateException("Cannot instantiate Common");
    }
    
    /**
     * An identity predicate, which returns true if item is non-null.
     * @param <T>
     * @return 
     */
    static <T> Predicate<T> iIdentityPredicate(){
        return i -> i != null;
    }
    
    /**
     * An identity ArrayPredicate, which returns true if item is non-null.
     * @param <T>
     * @return 
     */
    static <T> ArrayPredicate<T> iIdentityArrayPredicate(){
        return (v, i, a) -> v != null;
    }
    
    /**
     * An identity MapPredicate, which returns true if value is non-null.
     * @param <T>
     * @return 
     */
    static <K, V> MapPredicate<K, V> iIdentityMapPredicate(){
        return (v, i, a) -> v != null;
    }
    
    /**
     * Converts a predicate to an ArrayPredicate.
     * @param <T> The type the Predicate tests for.
     * @param predicate The predicate to convert.
     * @return The converted arrayPredicate.
     */
    static <T> ArrayPredicate<T> iArrayPredicateFromPredicate(Predicate<T> predicate){
        return (v, i, a) -> predicate.test(v);
    }
    
    /**
     * The identity function.
     * This function simply returns itself.
     * @param <T> The input and output types.
     * @return An identity function.
     */
    static <T> Function<T, T> iIdentity(){
        return t -> t;
    }
    
    /**
     * Creates a BiPredicate from a comparator.
     * @param <T> The type the comparator compares with.
     * @param comparator The comparator to convert.
     * @return A BiPredicate which works as the comparator did.
     */
    static <T> BiPredicate<T, T> iBiPredicateFromComparator(Comparator<T> comparator){
        return (T t, T u) -> comparator.compare(t, u) == 0;
    }
    
    /**
     * Creates an empty list.
     * @param <T> The type of the empty list.
     * @return An empty list.
     */
    static <T> List<T> iEmptyList(){
        return new ArrayList<>();
    }
    
    /**
     * Internal function to get a list of indexes from a list.
     * @param <T> The type in the list.
     * @param list The list to get the indexes of.
     * @return The list of indexes.
     */
    static <T> List<Integer> iGetIdsFromList(List<T> list){
        List<Integer> indices = new ArrayList<>();
        for(int index = 0; index < list.size(); index++){
            indices.add(index);
        }
        return indices;
    }
    
    /**
     * Internal function to turn a Predicate into a MapPredicate
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param predicate The predicate to wrap.
     * @return The Predicate as a MapPredicate.
     */
    static <K, V> MapPredicate<K, V> iMapPredicateFromPredicate(Predicate<V> predicate){
        return (v, i, c) -> predicate.test(v);
    }
    
    /**
     * Internal function to turn a Consumer into an ArrayConsumer
     * @param <T> The type contained in the list.
     * @param consumer The consumer to wrap.
     * @return The Consumer as an ArrayConsumer.
     */
    static <T> ArrayConsumer<T> iArrayConsumerFromConsumer(Consumer<T> consumer){
        return (v, i, c) -> consumer.accept(v);
    }
    
    /**
     * Internal function to turn a Consumer into a MapConsumer
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param consumer The consumer to wrap.
     * @return The Consumer as a MapConsumer.
     */
    static <K, V> MapConsumer<K, V> iMapConsumerFromConsumer(Consumer<V> consumer){
        return (v, i, c) -> consumer.accept(v);
    }
    
    /**
     * Internal function to turn a Function into an ArrayFunction
     * @param <T> The type in the list.
     * @param <R> The type returned by the function.
     * @param function The function to wrap.
     * @return The Function as an ArrayFunction
     */
    static <T, R> ArrayFunction<T, R> iArrayFunctionFromFunction(Function<T, R> function){
        return (v, i, c) -> function.apply(v);
    }
    
    /**
     * Internal function to turn a Function into a MapFunction
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param <R> The type returned by the function.
     * @param function The function to wrap.
     * @return The Function as a MapFunction
     */
    static <K, V, R> MapFunction<K, V, R> iMapFunctionFromFunction(Function<V, R> function){
        return (v, i, c) -> function.apply(v);
    }
}
