/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * A wrapper for a memoized BiFunction.
 * Like the original implementation in lodash, the memo is exposed, and can
 * be modified by the programmer.
 * @author Justis
 * @param <T> The type of the first arg.
 * @param <U> The type of the second arg.
 * @param <R> The type of the return value.
 * @param <K> The type of the key memo.
 */
public class MemoizedBiFunction<T, U, R, K> extends Memoized<K, R> implements BiFunction<T, U, R> {

    private final BiFunction<T, U, R> func;
    private final BiFunction<T, U, K> resolver;
    
    /**
     * Creates a memoized BiFunction from a BiFunction
     * @param func The function to wrap.
     * @param resolver The function to condense the arguments into a memoization key.
     */
    public MemoizedBiFunction(BiFunction<T, U, R> func, BiFunction<T, U, K> resolver){
        this.func = func;
        this.resolver = resolver;
    }
    
    @Override
    public R apply(T t, U u) {
        K key = resolver.apply(t, u);
        return getMemo().computeIfAbsent(key, i -> func.apply(t, u));
    }
}
