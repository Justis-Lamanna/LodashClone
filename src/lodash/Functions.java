/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import functions.MemoizedFunction;
import functions.DebounceConsumer;
import functions.DebounceNilFunction;
import functions.MemoizedBiFunction;
import interfaces.NilFunction;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import utils.Pair;

/**
 * Methods for functions.
 * Note: Many of these methods can be expanded in the future, to take advantage
 * of BiConsumer, BiFunction, and BiPredicate.
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
     * Curries a BiFunction.
     * Rather than needing to invoke the wrapped BiFunction with both values at
     * once, this allows you to stagger the invokations by applying the first argument,
     * followed by the second. Thus, func.apply(t, u) becomes curriedFunc.apply(t).apply(u).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param <R> The type of the result.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<T, U, R> func){
        Objects.requireNonNull(func);
        return (T t) -> (U u) -> func.apply(t, u);
    }
    
    /**
     * Curries a BiPredicate.
     * Rather than needing to invoke the wrapped BiPredicate with both values at
     * once, this allows you to stagger the invokations by applying the first argument,
     * followed by the second. Thus, func.apply(t, u) becomes curriedFunc.apply(t).apply(u).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U> Function<T, Predicate<U>> curry(BiPredicate<T, U> func){
        Objects.requireNonNull(func);
        return (T t) -> (U u) -> func.test(t, u);
    }
    
    /**
     * Curries a BiConsumer.
     * Rather than needing to invoke the wrapped BiConsumer with both values at
     * once, this allows you to stagger the invokations by applying the first argument,
     * followed by the second. Thus, func.apply(t, u) becomes curriedFunc.apply(t).apply(u).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U> Function<T, Consumer<U>> curry(BiConsumer<T, U> func){
        Objects.requireNonNull(func);
        return (T t) -> (U u) -> func.accept(t, u);
    }
    
    /**
     * Curries a BiFunction backwards.
     * Rather than needing to invoke the wrapped BiFunction with both values at
     * once, this allows you to stagger the invokations by applying the second argument,
     * followed by the first. Thus, func.apply(t, u) becomes curriedFunc.apply(u).apply(t).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param <R> The type of the result.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U, R> Function<U, Function<T, R>> curryRight(BiFunction<T, U, R> func){
        Objects.requireNonNull(func);
        return (U u) -> (T t) -> func.apply(t, u);
    }
    
    /**
     * Curries a BiPredicate backwards.
     * Rather than needing to invoke the wrapped BiPredicate with both values at
     * once, this allows you to stagger the invokations by applying the second argument,
     * followed by the first. Thus, func.apply(t, u) becomes curriedFunc.apply(u).apply(t).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U> Function<U, Predicate<T>> curryRight(BiPredicate<T, U> func){
        Objects.requireNonNull(func);
        return (U u) -> (T t) -> func.test(t, u);
    }
    
    /**
     * Curries a BiConsumer backwards.
     * Rather than needing to invoke the wrapped BiConsumer with both values at
     * once, this allows you to stagger the invokations by applying the second argument,
     * followed by the first. Thus, func.apply(t, u) becomes curriedFunc.apply(u).apply(t).
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param func The function to curry.
     * @return The curried function.
     */
    public static <T, U> Function<U, Consumer<T>> curryRight(BiConsumer<T, U> func){
        Objects.requireNonNull(func);
        return (U u) -> (T t) -> func.accept(t, u);
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                func.accept(arg);
                timer.cancel();
            }
        };
        timer.schedule(task, wait);
        return task;
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                func.invoke();
                timer.cancel();
            }
        };
        return task;
    }
    
    /**
     * Flip the arguments of a BiFunction.
     * @param <T> The type of the formerly first argument.
     * @param <U> The type of the formerly second argument.
     * @param <R> The type of the return value.
     * @param func The function to invert.
     * @return The function that accepts the flipped argument.
     */
    public static <T, U, R> BiFunction<U, T, R> flip(BiFunction<T, U, R> func){
        Objects.requireNonNull(func);
        return (U u, T t) -> func.apply(t, u);
    }
    
    /**
     * Flip the arguments of a BiPredicate.
     * @param <T> The type of the formerly first argument.
     * @param <U> The type of the formerly second argument.
     * @param func The function to invert.
     * @return The function that accepts the flipped argument.
     */
    public static <T, U> BiPredicate<U, T> flip(BiPredicate<T, U> func){
        Objects.requireNonNull(func);
        return (U u, T t) -> func.test(t, u);
    }
    
    /**
     * Flip the arguments of a BiConsumer.
     * @param <T> The type of the formerly first argument.
     * @param <U> The type of the formerly second argument.
     * @param func The function to invert.
     * @return The function that accepts the flipped argument.
     */
    public static <T, U> BiConsumer<U, T> flip(BiConsumer<T, U> func){
        Objects.requireNonNull(func);
        return (U u, T t) -> func.accept(t, u);
    }
    
    /**
     * Add memoization to a function.
     * Previous results are saved, and returned upon using the same
     * argument. The cache is exposed, for direct manipulation, or changing
     * to another type of map.
     * @param <T> The type of the input.
     * @param <R> The type of the output.
     * @param func The function to wrap.
     * @return A memoized function.
     */
    public static <T, R> MemoizedFunction<T, R> memoize(Function<T, R> func){
        Objects.requireNonNull(func);
        return new MemoizedFunction<>(func);
    }
    
    /**
     * Add memoization to a function.
     * Previous results are saved, and returned upon using the same
     * argument. The cache is exposed, for direct manipulation, or changing
     * to another type of map. The key of the memo is a MemoPair.
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param <R> The type of the return value.
     * @param func The function to wrap.
     * @return The memoized function.
     */
    public static <T, U, R> MemoizedBiFunction<T, U, R, Pair<T, U>> memoize(BiFunction<T, U, R> func){
        Objects.requireNonNull(func);
        return new MemoizedBiFunction<>(func, Pair::of);
    }
    
    /**
     * Add memoization to a function.
     * Previous results are saved, and returned upon using the same
     * argument. The cache is exposed, for direct manipulation, or changing
     * to another type of map. The key of the memo is determined by the resolver
     * function.
     * @param <T> The type of the first argument.
     * @param <U> The type of the second argument.
     * @param <R> The type of the return value.
     * @param <K> The type of the key generated.
     * @param func The function to wrap.
     * @param resolver The function to turn the parameters into a memo key.
     * @return The memoized function.
     */
    public static <T, U, R, K> MemoizedBiFunction<T, U, R, K> memoize(BiFunction<T, U, R> func, BiFunction<T, U, K> resolver){
        Objects.requireNonNull(func);
        return new MemoizedBiFunction<>(func, resolver);
    }
    
    /**
     * Negate the result of a predicate.
     * If the wrapped predicate would return true, the returned predicate
     * will return false, and vice-versa.
     * @param <T> The type of the input.
     * @param predicate The predicate to invert.
     * @return The inverted predicate.
     */
    public static <T> Predicate<T> negate(Predicate<T> predicate){
        return predicate.negate();
    }
    
    /**
     * Creates a function that invokes its wrapped function once.
     * @param <T> The type of the input of the function.
     * @param <R> The type of the output of the function.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T, R> Function<T, R> once(Function<T, R> func){
        return iDelayed(2, Objects.requireNonNull(func), null, true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once.
     * @param <T> The type of the output of the function.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T> Supplier<T> once(Supplier<T> func){
        return iDelayed(2, Objects.requireNonNull(func), null, true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once.
     * @param <T> The type of the input of the function.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static <T> Consumer<T> once(Consumer<T> func){
        return iDelayed(2, Objects.requireNonNull(func), true);
    }
    
    /**
     * Creates a function that invokes its wrapped function once.
     * @param func The function to wrap.
     * @return A wrapped function.
     */
    public static NilFunction once(NilFunction func){
        return iDelayed(2, Objects.requireNonNull(func), true);
    }
    
    /**
     * Throttles a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at once every wait milliseconds.
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
    public static <T> DebounceConsumer<T> throttle(Consumer<T> func, long wait, boolean trailingEdge){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceConsumer<>(func, wait, trailingEdge, true);
    }
    
    /**
     * Throttles a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at once every wait milliseconds.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param <T> The type the consumer consumes.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @return The wrapped function.
     */
    public static <T> DebounceConsumer<T> throttle(Consumer<T> func, long wait){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceConsumer<>(func, wait, true, true);
    }
    
    /**
     * Throttles a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at once every wait milliseconds.
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
    public static DebounceNilFunction throttle(NilFunction func, long wait, boolean trailingEdge){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceNilFunction(func, wait, trailingEdge, true);
    }
    
    /**
     * Throttles a function.
     * This ensures that, in the case of multiple function calls, the function
     * is called only at once every wait milliseconds.
     * 
     * The returned consumer also contains methods for immediate invokation,
     * as well as canceling. Once a function is canceled, it always executes
     * immediately.
     * @param func The function to wrap.
     * @param wait The number of milliseconds to wait before triggering.
     * @return The wrapped function.
     */
    public static DebounceNilFunction throttle(NilFunction func, long wait){
        Objects.requireNonNull(func);
        if(wait < 0){
            throw new IllegalArgumentException("Wait must be non-negative");
        }
        return new DebounceNilFunction(func, wait, true, true);
    }
}
