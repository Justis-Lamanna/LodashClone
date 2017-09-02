/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

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
     * @return Lower if number is less than lower, upper if number is less
     * than upper, number otherwise.
     */
    public static BigDecimal clamp(BigDecimal number, BigDecimal lower, BigDecimal upper){
        Objects.requireNonNull(number);
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
     * @return Lower if number is less than lower, upper if number is less
     * than upper, number otherwise.
     */
    public static BigInteger clamp(BigInteger number, BigInteger lower, BigInteger upper){
        Objects.requireNonNull(number);
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
     * @return Lower if number is less than lower, upper if number is less
     * than upper, number otherwise.
     */
    public static <T extends Number> T clamp(T number, T lower, T upper){
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
        if(number.doubleValue() > upper.doubleValue()){
            return upper;
        } else {
            return number;
        }
    }
}
