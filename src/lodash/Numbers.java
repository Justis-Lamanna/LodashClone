/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

/**
 * Number functions.
 * @author Justis
 */
public class Numbers {
    
    private Numbers(){
        throw new IllegalStateException("Cannot instantiate Numbers");
    }
    
    
    /**
     * Force a number to be between two numbers.
     * @param number The number to test.
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return Lower if number is less than lower, upper if number is greater
     * than upper, number otherwise.
     */
    public static BigDecimal clamp(BigDecimal number, BigDecimal lower, BigDecimal upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(lower);
        Objects.requireNonNull(upper);
        if(number.compareTo(lower) < 0){
            return lower;
        } else if(number.compareTo(upper) > 0){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Force a number to be less than some number.
     * @param number The number to test.
     * @param upper The upper bound.
     * @return Upper is number is greater than upper, number otherwise.
     */
    public static BigDecimal clamp(BigDecimal number, BigDecimal upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(upper);
        if(number.compareTo(upper) > 0){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Force a number to be between two numbers.
     * @param number The number to test.
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return Lower if number is less than lower, upper if number is greater
     * than upper, number otherwise.
     */
    public static BigInteger clamp(BigInteger number, BigInteger lower, BigInteger upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(lower);
        Objects.requireNonNull(upper);
        if(number.compareTo(lower) < 0){
            return lower;
        } else if(number.compareTo(upper) > 0){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Force a number to be less than some number.
     * @param number The number to test.
     * @param upper The upper bound.
     * @return Upper is number is greater than upper, number otherwise.
     */
    public static BigInteger clamp(BigInteger number, BigInteger upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(upper);
        if(number.compareTo(upper) > 0){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Force a number to be between two numbers.
     * @param <T> The type of number.
     * @param number The number to test.
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return Lower if number is less than lower, upper if number is greater
     * than upper, number otherwise.
     */
    public static <T extends Number> T clamp(T number, T lower, T upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(lower);
        Objects.requireNonNull(upper);
        if(number.doubleValue() < lower.doubleValue()){
            return lower;
        } else if(number.doubleValue() > upper.doubleValue()){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Force a number to below some number.
     * @param <T> The type of number.
     * @param number The number to test.
     * @param upper The upper bound.
     * @return Upper is number is greater than upper, number otherwise.
     */
    public static <T extends Number> T clamp(T number, T upper){
        Objects.requireNonNull(number);
        Objects.requireNonNull(upper);
        if(number.doubleValue() > upper.doubleValue()){
            return upper;
        } else {
            return number;
        }
    }
    
    /**
     * Tests if a number is between start(inclusive) and end(exclusive).
     * If start is greater than end, start and end are exchanged.
     * @param <T> The type of number.
     * @param number The number to test.
     * @param start The lower number of the range.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static <T extends Number> boolean inRange(T number, T start, T end){
        Objects.requireNonNull(number);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        T fStart = start;
        T fEnd = end;
        if(start.doubleValue() > end.doubleValue()){
            fStart = end;
            fEnd = start;
        }
        return fStart.doubleValue() <= number.doubleValue() 
                && fEnd.doubleValue() > number.doubleValue();
    }
    
    /**
     * Tests if a number is between zero(inclusive) and end(exclusive).
     * @param <T> The type of number.
     * @param number The number to test.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static <T extends Number> boolean inRange(T number, T end){
        return inRange(number, 0, end);
    }
    
    /**
     * Tests if a number is between start(inclusive) and end(exclusive).
     * If start is greater than end, start and end are exchanged.
     * @param number The number to test.
     * @param start The lower number of the range.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static boolean inRange(BigInteger number, BigInteger start, BigInteger end){
        Objects.requireNonNull(number);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        BigInteger fStart = start;
        BigInteger fEnd = end;
        if(start.compareTo(end) > 0){
            fStart = end;
            fEnd = start;
        }
        return fStart.compareTo(number) <= 0
                && fEnd.compareTo(number) > 0;
    }
    
    /**
     * Tests if a number is between zero(inclusive) and end(exclusive).
     * @param number The number to test.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static boolean inRange(BigInteger number, BigInteger end){
        return inRange(number, BigInteger.ZERO, end);
    }
    
    /**
     * Tests if a number is between start(inclusive) and end(exclusive).
     * If start is greater than end, start and end are exchanged.
     * @param number The number to test.
     * @param start The lower number of the range.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static boolean inRange(BigDecimal number, BigDecimal start, BigDecimal end){
        Objects.requireNonNull(number);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        BigDecimal fStart = start;
        BigDecimal fEnd = end;
        if(start.compareTo(end) > 0){
            fStart = end;
            fEnd = start;
        }
        return fStart.compareTo(number) <= 0
                && fEnd.compareTo(number) > 0;
    }
    
    /**
     * Tests if a number is between zero(inclusive) and end(exclusive).
     * @param number The number to test.
     * @param end The upper number of the range.
     * @return True if the number is in the range, false if not.
     */
    public static boolean inRange(BigDecimal number, BigDecimal end){
        return inRange(number, BigDecimal.ZERO, end);
    }
    
    /**
     * Generate a random integer between lower(inclusive) and upper(exclusive).
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return The random integer.
     */
    public static int random(int lower, int upper){
        if(lower == upper){
            return lower;
        }
        Random rnd = new Random();
        if(lower > upper){
            return upper + rnd.nextInt(lower - upper);
        } else {
            return lower + rnd.nextInt(upper - lower);
        }
    }
    
    /**
     * Generate a random integer between zero(inclusive) and upper(exclusive).
     * @param upper The upper bound.
     * @return The random integer.
     */
    public static int random(int upper){
        return random(0, upper);
    }
    
    /**
     * Generate a random double between lower(inclusive) and upper(exclusive).
     * @param lower The lower bound.
     * @param upper The upper bound.
     * @return A random double between lower and upper.
     */
    public static double random(double lower, double upper){
        if(lower > upper){
            return lower + (upper - lower) * Math.random();
        } else {
            return upper + (lower - upper) * Math.random();
        }
    }
    
    /**
     * Generate a random double between zero(inclusive) and upper(exclusive).
     * @param upper The upper bound.
     * @return The random integer.
     */
    public static double random(double upper){
        return random(0, upper);
    }
    
    /**
     * Generate a random double between zero(inclusive) and one(exclusive).
     * @return The random integer.
     */
    public static double random(){
        return random(0.0, 1.0);
    }
}
