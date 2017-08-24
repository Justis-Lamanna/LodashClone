/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.Arrays;
import java.util.List;

/**
 * Some utilities for Lodash clone stuff.
 * @author justislamanna
 */
public class LodashUtil {
    
    /**
     * A list of falsey byte values.
     */
    public static List<Byte> FALSEY_BYTE = Arrays.asList(null, (byte)0);
    
    /**
     * A list of falsey short values.
     */
    public static List<Short> FALSEY_SHORT = Arrays.asList(null, (short)0);
    
    /**
     * A list of falsey integer values.
     */
    public static List<Integer> FALSEY_INTEGER = Arrays.asList(null, 0);
    
    /**
     * A list of falsey long values.
     */
    public static List<Long> FALSEY_LONG = Arrays.asList(null, 0L);
    
    /**
     * A list of falsey float values.
     */
    public static List<Float> FALSEY_FLOAT = Arrays.asList(null, 0.0f, Float.NaN);
    
    /**
     * A list of falsey double values.
     */
    public static List<Double> FALSEY_DOUBLE = Arrays.asList(null, 0.0, Double.NaN);
    
    /**
     * A list of falsey string values.
     */
    public static List<String> FALSEY_STRING = Arrays.asList(null, "");
    
    /**
     * A list of falsey boolean values.
     */
    public static List<Boolean> FALSEY_BOOLEAN = Arrays.asList(null, Boolean.FALSE);
}
