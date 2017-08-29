/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import interfaces.NilFunction;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Methods for functions.
 * @author Justis
 */
public class Functions {
    
    private Functions(){
        throw new IllegalStateException("Cannot instantiate Functions");
    }
    
    /**
     * Internal function for delayed invocation
     * @param <T> The type of the input of the function.
     * @param <R> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param untilCalled The value to return until the wrapped function is invoked.
     * @param stopAtZero True to stop at zero, false to start at zero.
     * @return A wrapped function.
     */
    static <T, R> Function<T, R> iDelayed(int n, Function<T, R> func, R untilCalled, boolean stopAtZero){
        return new Function<T, R>(){
            int remaining = n;
            R last = untilCalled;
            @Override
            public R apply(T t) {
                remaining = (remaining <= 0) ? 0 : remaining - 1;
                if(!(remaining == 0 ^ stopAtZero)){
                    return last;
                } else {
                    last = func.apply(t);
                    return last;
                }
            }
        };
    }
    
    /**
     * Internal function for delayed invocation
     * @param <T> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param untilCalled The value to return until the wrapped function is invoked.
     * @param stopAtZero True to stop at zero, false to start at zero.
     * @return A wrapped function.
     */
    static <T> Supplier<T> iDelayed(int n, Supplier<T> func, T untilCalled, boolean stopAtZero){
        return new Supplier<T>(){
            int remaining = n;
            T last = untilCalled;
            @Override
            public T get() {
                remaining = (remaining <= 0) ? 0 : remaining - 1;
                if(!(remaining == 0 ^ stopAtZero)){
                    return last;
                } else {
                    last = func.get();
                    return last;
                }
            }
        };
    }
    
    /**
     * Internal function for delayed invocation
     * @param <T> The type of the input of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param stopAtZero True to stop at zero, false to start at zero.
     * @return A wrapped function.
     */
    static <T> Consumer<T> iDelayed(int n, Consumer<T> func, boolean stopAtZero){
        return new Consumer<T>(){
            int remaining = n;
            @Override
            public void accept(T t) {
                remaining = (remaining <= 0) ? 0 : remaining - 1;
                if(remaining == 0 ^ stopAtZero){
                    func.accept(t);
                }
            }
        };
    }
    
    /**
     * Internal function for delayed invocation
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param stopAtZero True to stop at zero, false to start at zero.
     * @return A wrapped function.
     */
    static NilFunction iDelayed(int n, NilFunction func, boolean stopAtZero){
        return new NilFunction(){
            int remaining = n;
            @Override
            public void invoke() {
                remaining = (remaining <= 0) ? 0 : remaining - 1;
                if(remaining == 0 ^ stopAtZero){
                    func.invoke();
                }
            }
        };
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the input of the function.
     * @param <R> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param untilCalled The value to return until the wrapped function is invoked.
     * @return A wrapped function.
     */
    public static <T, R> Function<T, R> after(int n, Function<T, R> func, R untilCalled){
        return iDelayed(n, Objects.requireNonNull(func), untilCalled, false);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @param untilCalled The value to return until the wrapped function is invoked.
     * @return A wrapped function.
     */
    public static <T> Supplier<T> after(int n, Supplier<T> func, T untilCalled){
        return iDelayed(n, Objects.requireNonNull(func), untilCalled, false);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the input of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T> Consumer<T> after(int n, Consumer<T> func){
        return iDelayed(n, Objects.requireNonNull(func), false);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static NilFunction after(int n, NilFunction func){
        return iDelayed(n, Objects.requireNonNull(func), false);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the input of the function.
     * @param <R> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T, R> Function<T, R> before(int n, Function<T, R> func){
        return iDelayed(n, Objects.requireNonNull(func), null, true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the output of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T> Supplier<T> before(int n, Supplier<T> func){
        return iDelayed(n, Objects.requireNonNull(func), null, true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param <T> The type of the input of the function.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T> Consumer<T> before(int n, Consumer<T> func){
        return iDelayed(n, Objects.requireNonNull(func), true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once it's called n times.
     * @param n The number of times to wait until called.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static NilFunction before(int n, NilFunction func){
        return iDelayed(n, Objects.requireNonNull(func), true);
    }
}
