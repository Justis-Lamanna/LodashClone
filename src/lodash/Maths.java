/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static BigDecimal ceil(BigDecimal number, int precision){
        //ceil(number * 10^precision) / 10^precision.
        BigDecimal prec;
        if(precision < 0){
            prec = BigDecimal.ONE
                    .divide(BigDecimal.TEN
                            .pow(-precision));
        } else {
            prec = BigDecimal.TEN
                    .pow(precision);
        }
        return prec
                .multiply(number)
                .setScale(0, RoundingMode.CEILING)
                .divide(prec);
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static BigDecimal ceil(BigDecimal number){
        return ceil(number, 0);
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static BigInteger ceil(BigInteger number, int precision){
        //ceil(number * 10^precision) / 10^precision.
        if(precision >= 0){
            return number;
        }
        BigDecimal prec = BigDecimal.ONE
                .divide(BigDecimal.TEN.pow(-precision));
        return prec
                .multiply(new BigDecimal(number))
                .setScale(0, RoundingMode.CEILING)
                .divide(prec)
                .toBigInteger();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static BigInteger ceil(BigInteger number){
        return ceil(number, 0);
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static int ceil(int number, int precision){
        return ceil(BigInteger.valueOf(number), precision).intValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static int ceil(int number){
        return ceil(BigInteger.valueOf(number), 0).intValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static long ceil(long number, int precision){
        return ceil(BigInteger.valueOf(number), precision).longValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static long ceil(long number){
        return ceil(BigInteger.valueOf(number), 0).longValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static float ceil(float number, int precision){
        return ceil(BigDecimal.valueOf(number), precision).floatValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static float ceil(float number){
        return ceil(BigDecimal.valueOf(number), 0).floatValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static double ceil(double number, int precision){
        return ceil(BigDecimal.valueOf(number), precision).doubleValue();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @return The ceiling'ed number.
     */
    public static double ceil(double number){
        return ceil(BigDecimal.valueOf(number), 0).doubleValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static BigDecimal floor(BigDecimal number, int precision){
        //floor(number * 10^precision) / 10^precision.
        BigDecimal prec;
        if(precision < 0){
            prec = BigDecimal.ONE
                    .divide(BigDecimal.TEN
                            .pow(-precision));
        } else {
            prec = BigDecimal.TEN
                    .pow(precision);
        }
        return prec
                .multiply(number)
                .setScale(0, RoundingMode.FLOOR)
                .divide(prec);
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static BigDecimal floor(BigDecimal number){
        return floor(number, 0);
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static BigInteger floor(BigInteger number, int precision){
        //floor(number * 10^precision) / 10^precision.
        if(precision >= 0){
            return number;
        }
        BigDecimal prec = BigDecimal.ONE
                .divide(BigDecimal.TEN.pow(-precision));
        return prec
                .multiply(new BigDecimal(number))
                .setScale(0, RoundingMode.FLOOR)
                .divide(prec)
                .toBigInteger();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static BigInteger floor(BigInteger number){
        return floor(number, 0);
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static int floor(int number, int precision){
        return floor(BigInteger.valueOf(number), precision).intValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static int floor(int number){
        return floor(BigInteger.valueOf(number), 0).intValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static long floor(long number, int precision){
        return floor(BigInteger.valueOf(number), precision).longValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static long floor(long number){
        return floor(BigInteger.valueOf(number), 0).longValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static float floor(float number, int precision){
        return floor(BigDecimal.valueOf(number), precision).floatValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static float floor(float number){
        return floor(BigDecimal.valueOf(number), 0).floatValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static double floor(double number, int precision){
        return floor(BigDecimal.valueOf(number), precision).doubleValue();
    }
    
    /**
     * Get the floor of a number.
     * @param number The number to floor.
     * @return The floor'ed number.
     */
    public static double floor(double number){
        return floor(BigDecimal.valueOf(number), 0).doubleValue();
    }
}
