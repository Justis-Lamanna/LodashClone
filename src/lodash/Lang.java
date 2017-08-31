/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * General functions
 * @author justislamanna
 */
public class Lang {
    
    private Lang(){
        throw new IllegalStateException("Cannot instantiate Lang");
    }
    
    /**
     * Test if a map conforms to another map.
     * All predicates in the source are applied to their corresponding
     * values in object. Returns true if all passed, or false if some failed.
     * @param <K> The type of the keys.
     * @param <V> The type of the values.
     * @param object The object to test.
     * @param source The source of the predicates.
     * @return True if the object conforms, false if not.
     */
    public static <K, V> boolean conformsTo(Map<K, V> object, Map<K, Predicate<V>> source){
        for(K key : source.keySet()){
            if(object.containsKey(key)){
                V valueOfObject = object.get(key);
                Predicate<V> value = source.get(key);
                if(!value.test(valueOfObject)){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Tests if two objects are equal.
     * @param value The first value.
     * @param other The second value.
     * @return True if both are equal, false if not.
     */
    public static boolean eq(Object value, Object other){
        return Objects.deepEquals(value, other);
    }
    
    /**
     * Compare two values.
     * @param <T> The type of the values.
     * @param value The first value.
     * @param other The second value.
     * @param comparator The comparator.
     * @param compVal Which branch should be use.
     * @return 
     */
    static <T> boolean iCompare(T value, T other, Comparator<T> comparator, BiPredicate<Integer, Integer> comparison){
        int compared = Objects.compare(value, other, comparator);
        return comparison.test(compared, 0);
    }
    
    /**
     * Checks if a value is greater than another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @return True if value is greater than other.
     */
    public static <T extends Comparable> boolean gt(T value, T other){
        return iCompare(value, other, Comparator.naturalOrder(), (t, u) -> t > u);
    }
    
    /**
     * Checks if a value is greater than another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @param comparator The comparator to use.
     * @return True if value is greater than other.
     */
    public static <T> boolean gt(T value, T other, Comparator<T> comparator){
        return iCompare(value, other, comparator, (t, u) -> t > u);
    }
    
    /**
     * Checks if a value is greater than or equal to another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @return True if value is greater than or equal to other.
     */
    public static <T extends Comparable> boolean gte(T value, T other){
        return iCompare(value, other, Comparator.naturalOrder(), (t, u) -> t >= u);
    }
    
    /**
     * Checks if a value is greater than or equal to another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @param comparator The comparator to use.
     * @return True if value is greater than or equal to other.
     */
    public static <T> boolean gte(T value, T other, Comparator<T> comparator){
        return iCompare(value, other, comparator, (t, u) -> t >= u);
    }
    
    /**
     * Checks if a value is less than another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @return True if value is less than other.
     */
    public static <T extends Comparable> boolean lt(T value, T other){
        return iCompare(value, other, Comparator.naturalOrder(), (t, u) -> t < u);
    }
    
    /**
     * Checks if a value is less than another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @param comparator The comparator to use.
     * @return True if value is less than other.
     */
    public static <T> boolean lt(T value, T other, Comparator<T> comparator){
        return iCompare(value, other, comparator, (t, u) -> t < u);
    }
    
    /**
     * Checks if a value is less than or equal to another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @return True if value is less than or equal to other.
     */
    public static <T extends Comparable> boolean lte(T value, T other){
        return iCompare(value, other, Comparator.naturalOrder(), (t, u) -> t <= u);
    }
    
    /**
     * Checks if a value is less than or equal to another.
     * @param <T> The type of the operands.
     * @param value The first value.
     * @param other The second value.
     * @param comparator The comparator to use.
     * @return True if value is less than or equal to other.
     */
    public static <T> boolean lte(T value, T other, Comparator<T> comparator){
        return iCompare(value, other, comparator, (t, u) -> t <= u);
    }
}
