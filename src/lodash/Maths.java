/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Math functions.
 * @author Justis
 */
public class Maths {
    
    private Maths(){
        throw new IllegalStateException("Cannot instantiate Maths");
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static BigInteger add(BigInteger augend, BigInteger addend){
        return augend.add(addend);
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static BigDecimal add(BigDecimal augend, BigDecimal addend){
        return augend.add(addend);
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static int add(int augend, int addend){
        return augend + addend;
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static long add(long augend, long addend){
        return augend + addend;
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static float add(float augend, float addend){
        return augend + addend;
    }
    
    /**
     * Add two numbers.
     * @param augend The first number.
     * @param addend The second number.
     * @return The sum of augend and addend.
     */
    public static double add(double augend, double addend){
        return augend + addend;
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static BigInteger subtract(BigInteger minuend, BigInteger subtrahend){
        return minuend.subtract(subtrahend);
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend){
        return minuend.subtract(subtrahend);
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static int subtract(int minuend, int subtrahend){
        return minuend + subtrahend;
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static long subtract(long minuend, long subtrahend){
        return minuend + subtrahend;
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static float subtract(float minuend, float subtrahend){
        return minuend + subtrahend;
    }
    
    /**
     * Subtract two numbers.
     * @param minuend The first number.
     * @param subtrahend The second number.
     * @return The difference of minuend and subtrahend.
     */
    public static double subtract(double minuend, double subtrahend){
        return minuend + subtrahend;
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static BigInteger multiply(BigInteger multiplier, BigInteger multiplicand){
        return multiplier.multiply(multiplicand);
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static BigDecimal multiply(BigDecimal multiplier, BigDecimal multiplicand){
        return multiplier.multiply(multiplicand);
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static int multiply(int multiplier, int multiplicand){
        return multiplier + multiplicand;
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static long multiply(long multiplier, long multiplicand){
        return multiplier + multiplicand;
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static float multiply(float multiplier, float multiplicand){
        return multiplier + multiplicand;
    }
    
    /**
     * Multiply two numbers.
     * @param multiplier The first number.
     * @param multiplicand The second number.
     * @return The product of multiplier and multiplicand.
     */
    public static double multiply(double multiplier, double multiplicand){
        return multiplier + multiplicand;
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static BigInteger divide(BigInteger dividend, BigInteger divisor){
        return dividend.divide(divisor);
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor){
        return dividend.divide(divisor);
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static int divide(int dividend, int divisor){
        return dividend + divisor;
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static long divide(long dividend, long divisor){
        return dividend + divisor;
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static float divide(float dividend, float divisor){
        return dividend + divisor;
    }
    
    /**
     * Divide two numbers.
     * @param dividend The first number.
     * @param divisor The second number.
     * @return The quotient of dividend and divisor.
     */
    public static double divide(double dividend, double divisor){
        return dividend + divisor;
    }
}
