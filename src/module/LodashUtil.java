/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Some utilities for Lodash clone stuff.
 * @author justislamanna
 */
public class LodashUtil {
    
    /**
     * A list of falsey byte values.
     */
    public static List<Byte> FALSEY_BYTES = Arrays.asList(null, (byte)0);
    
    /**
     * A list of falsey short values.
     */
    public static List<Short> FALSEY_SHORTS = Arrays.asList(null, (short)0);
    
    /**
     * A list of falsey integer values.
     */
    public static List<Integer> FALSEY_INTEGERS = Arrays.asList(null, 0);
    
    /**
     * A list of falsey long values.
     */
    public static List<Long> FALSEY_LONGS = Arrays.asList(null, 0L);
    
    /**
     * A list of falsey float values.
     */
    public static List<Float> FALSEY_FLOATS = Arrays.asList(null, 0.0f, Float.NaN);
    
    /**
     * A list of falsey double values.
     */
    public static List<Double> FALSEY_DOUBLES = Arrays.asList(null, 0.0, Double.NaN);
    
    /**
     * A list of falsey string values.
     */
    public static List<String> FALSEY_STRINGS = Arrays.asList(null, "");
    
    /**
     * A list of falsey boolean values.
     */
    public static List<Boolean> FALSEY_BOOLEANS = Arrays.asList(null, Boolean.FALSE);
    
    /**
     * Converts a list of invalid values into a predicate.
     * @param <T> The type contained in the list of falsey values.
     * @param list The list of falsey values.
     * @return A predicate which returns true if the supplied value is not in 
     * the list of invalid values.
     */
    private static <T> Predicate<T> isNotInList(List<T> list){
        return t -> list.contains(t);
    }
    
    /**
     * A predicate that returns true if the supplied byte is truthy.
     */
    public static final Predicate<Byte> VALID_BYTES = isNotInList(FALSEY_BYTES);
    
    /**
     * A predicate that returns true if the supplied short is truthy.
     */
    public static final Predicate<Short> VALID_SHORTS = isNotInList(FALSEY_SHORTS);
    
    /**
     * A predicate that returns true if the supplied integer is truthy.
     */
    public static final Predicate<Integer> VALID_INTEGERS = isNotInList(FALSEY_INTEGERS);
    
    /**
     * A predicate that returns true if the supplied long is truthy.
     */
    public static final Predicate<Long> VALID_LONGS = isNotInList(FALSEY_LONGS);
    
    /**
     * A predicate that returns true if the supplied float is truthy.
     */
    public static final Predicate<Float> VALID_FLOATS = isNotInList(FALSEY_FLOATS);
    
    /**
     * A predicate that returns true if the supplied double is truthy.
     */
    public static final Predicate<Double> VALID_DOUBLES = isNotInList(FALSEY_DOUBLES);
    
    /**
     * A predicate that returns true if the supplied string is truthy.
     */
    public static final Predicate<String> VALID_STRINGS = isNotInList(FALSEY_STRINGS);
}
