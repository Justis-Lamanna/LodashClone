/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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
     * Internal function to round a value in some way to some precision
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @param round The type of rounding to do.
     * @return The rounded number.
     */
    static BigDecimal iRound(BigDecimal number, int precision, RoundingMode round){
        //[ceil/floor/round](number * 10^precision) / 10^precision.
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
                .setScale(0, round)
                .divide(prec);
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @param round The type of rounding to do.
     * @return The ceiling'ed number.
     */
    public static BigInteger iRound(BigInteger number, int precision, RoundingMode round){
        //ceil(number * 10^precision) / 10^precision.
        if(precision >= 0){
            return number;
        }
        BigDecimal prec = BigDecimal.ONE
                .divide(BigDecimal.TEN.pow(-precision));
        return prec
                .multiply(new BigDecimal(number))
                .setScale(0, round)
                .divide(prec)
                .toBigInteger();
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static BigDecimal ceil(BigDecimal number, int precision){
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.CEILING);
    }
    
    /**
     * Get the ceiling of a number.
     * @param number The number to ceiling.
     * @param precision The precision to ceiling to.
     * @return The ceiling'ed number.
     */
    public static BigInteger ceil(BigInteger number, int precision){
        //ceil(number * 10^precision) / 10^precision.
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.CEILING);
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
    public static int ceil(int number, int precision){
        return ceil(BigInteger.valueOf(number), precision).intValue();
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
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.FLOOR);
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
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.FLOOR);
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
     * @param precision The precision to floor to.
     * @return The floor'ed number.
     */
    public static long floor(long number, int precision){
        return floor(BigInteger.valueOf(number), precision).longValue();
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
    
    /**
     * Finds the maximum number in a list.
     * @param <T> The type of number.
     * @param array The array to search.
     * @return The max in the list.
     */
    public static <T extends Number> T max(List<T> array){
        return maxBy(array, Function.identity());
    }
    
    /**
     * Find the maximum number in a list, using a function to determine the sort criteria.
     * @param <T> The type in the array.
     * @param <R> The type of number to map to.
     * @param array The array to search.
     * @param iteratee The mapping function.
     * @return The maximum in the array.
     */
    public static <T, R extends Number> T maxBy(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        if(array.isEmpty()){
            return null;
        }
        T maximum = array.get(0);
        for(int index = 1; index < array.size(); index++){
            if(iteratee.apply(array.get(index)).doubleValue() > 
                    iteratee.apply(maximum).doubleValue()){
                maximum = array.get(index);
            }
        }
        return maximum;
    }
    
    /**
     * Find the mean of a list of numbers.
     * @param <T> The type of number.
     * @param array The array to average.
     * @return The average of the list.
     */
    public static <T extends Number> double mean(List<T> array){
        return meanBy(array, Function.identity());
    }
    
    /**
     * Find the mean of a list of objects, after running them through an iteratee.
     * NaN is returned if the list is empty.
     * @param <T> The type in the list.
     * @param <R> The type of number to map to.
     * @param array The array to average.
     * @param iteratee The mapping function.
     * @return The average of the list.
     */
    public static <T, R extends Number> double meanBy(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        if(array.isEmpty()){
            return Double.NaN;
        }
        double sum = 0.0;
        for(T value : array){
            R mappedValue = iteratee.apply(value);
            sum += mappedValue.doubleValue();
        }
        return sum / array.size();
    }
    
    /**
     * Finds the maximum number in a list.
     * @param <T> The type of number.
     * @param array The array to search.
     * @return The max in the list.
     */
    public static <T extends Number> T min(List<T> array){
        return minBy(array, Function.identity());
    }
    
    /**
     * Find the maximum number in a list, using a function to determine the sort criteria.
     * @param <T> The type in the array.
     * @param <R> The type of number to map to.
     * @param array The array to search.
     * @param iteratee The mapping function.
     * @return The maximum in the array.
     */
    public static <T, R extends Number> T minBy(List<T> array, Function<T, R> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        if(array.isEmpty()){
            return null;
        }
        T minimum = array.get(0);
        for(int index = 1; index < array.size(); index++){
            if(iteratee.apply(array.get(index)).doubleValue() < 
                    iteratee.apply(minimum).doubleValue()){
                minimum = array.get(index);
            }
        }
        return minimum;
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static BigDecimal round(BigDecimal number, int precision){
        //round(number * 10^precision) / 10^precision.
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.HALF_UP);
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @return The round'ed number.
     */
    public static BigDecimal round(BigDecimal number){
        return round(number, 0);
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static BigInteger round(BigInteger number, int precision){
        //round(number * 10^precision) / 10^precision.
        Objects.requireNonNull(number);
        return iRound(number, precision, RoundingMode.HALF_UP);
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static int round(int number, int precision){
        return round(BigInteger.valueOf(number), precision).intValue();
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static long round(long number, int precision){
        return round(BigInteger.valueOf(number), precision).longValue();
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static float round(float number, int precision){
        return round(BigDecimal.valueOf(number), precision).floatValue();
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @return The round'ed number.
     */
    public static float round(float number){
        return round(BigDecimal.valueOf(number), 0).floatValue();
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @param precision The precision to round to.
     * @return The round'ed number.
     */
    public static double round(double number, int precision){
        return round(BigDecimal.valueOf(number), precision).doubleValue();
    }
    
    /**
     * Get the round of a number.
     * @param number The number to round.
     * @return The round'ed number.
     */
    public static double round(double number){
        return round(BigDecimal.valueOf(number), 0).doubleValue();
    }
    
    /**
     * Finds the sum of a list.
     * @param <T> The type of number.
     * @param array The array to sum.
     * @return The sum of the list.
     */
    public static <T extends Number> double sum(List<T> array){
        return sumBy(array, i -> i.doubleValue());
    }
    
    /**
     * Find the sum of a list, using a mapping function to extract the number to sum.
     * @param <T> The type in the array.
     * @param array The array to sum.
     * @param iteratee The mapping function.
     * @return The sum of the array.
     */
    public static <T> double sumBy(List<T> array, Function<T, Number> iteratee){
        Objects.requireNonNull(array);
        Objects.requireNonNull(iteratee);
        return array.stream()
                .map(iteratee)
                .reduce(0, (s, a) -> s.doubleValue() + a.doubleValue()).doubleValue();
    }
}
