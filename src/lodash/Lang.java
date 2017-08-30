/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
     * Returns the provided collection as a list.
     * This is part of the programming of lodash's castArray: Anything that is
     * a collection returns itself, otherwise it is wrapped in a list. Lists in java
     * will match this method first: Everything else will go to the other version.
     * @param <T> The type in the list.
     * @param list The list to return.
     * @return The returned list.
     */
    public static <T> List<T> castArray(Collection<T> list){
        //Null matches this version. Lodash returns null wrapped in a list, so we do that here.
        if(list == null){
            return java.util.Arrays.asList((T) null);
        }
        return new ArrayList<>(list);
    }
    
    /**
     * Returns the provided value wrapped in a list.
     * This is part of the programming of lodash's castArray: Anything that is
     * a list returns itself, otherwise it is wrapped in a list. Lists in java
     * will match the other method first: Everything else will go to this version.
     * @param <T> The type in the list.
     * @param value The value to wrap.
     * @return The returned list.
     */
    public static <T> List<T> castArray(T value){
        return java.util.Arrays.asList(value);
    }
    
    /**
     * Returns an empty list.
     * @param <T> The type expected of the returned list.
     * @return An empty list.
     */
    public static <T> List<T> castArray(){
        return java.util.Collections.emptyList();
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
}
