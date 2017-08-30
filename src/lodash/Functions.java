/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import functions.DebounceConsumer;
import functions.DebounceNilFunction;
import interfaces.NilFunction;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
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
    
    /**
     * Debounces a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at the end or beginning of the calls.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param <T> The type the consumer consumes.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @param trailingEdge True if the function to activate at the trailing edge,
     * or false if it should activate on the leading edge.
     * @return The wrapped function.
     */
    public static <T> DebounceConsumer<T> debounce(Consumer<T> func, long wait, boolean trailingEdge){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceConsumer<>(func, wait, trailingEdge);
    }
    
    /**
     * Debounces a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at the end of the calls.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param <T> The type the consumer consumes.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @return The wrapped function.
     */
    public static <T> DebounceConsumer<T> debounce(Consumer<T> func, long wait){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceConsumer<>(func, wait, true);
    }
    
    /**
     * Debounces a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at the end or beginning of the calls.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @param trailingEdge True if the function to activate at the trailing edge,
     * or false if it should activate on the leading edge.
     * @return The wrapped function.
     */
    public static DebounceNilFunction debounce(NilFunction func, long wait, boolean trailingEdge){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceNilFunction(func, wait, trailingEdge);
    }
    
    /**
     * Debounces a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at the end of the calls.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @return The wrapped function.
     */
    public static DebounceNilFunction debounce(NilFunction func, long wait){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceNilFunction(func, wait, true);
    }
    
    /**
     * Internal function for delay
     * @param task The task to delay.
     * @param wait The number of milliseconds to delay by.
     * @return The task reference;
     */
    static TimerTask iDelay(TimerTask task, long wait){
        Timer timer = new Timer();
        timer.schedule(task, wait);
        return task;
    }
    
    /**
     * Delays executing a function for some time.
     * @param <T> The type the consumer accepts.
     * @param func The function to execute.
     * @param wait The number of milliseconds to wait.
     * @param arg The argument to invoke the consumer with.
     * @return The TimerTask which executes the function.
     */
    public static <T> TimerTask delay(Consumer<T> func, long wait, T arg){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                func.accept(arg);
            }
        };
        return iDelay(task, wait);
    }
    
    /**
     * Delays executing a function for some time.
     * @param func The function to execute.
     * @param wait The number of milliseconds to wait.
     * @return The TimerTask which executes the function.
     */
    public static TimerTask delay(NilFunction func, long wait){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                func.invoke();
            }
        };
        return iDelay(task, wait);
    }
}
