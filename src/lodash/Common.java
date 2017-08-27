/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.function.Function;
import java.util.function.Predicate;
import module.ArrayPredicate;
import module.MapPredicate;

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
}
