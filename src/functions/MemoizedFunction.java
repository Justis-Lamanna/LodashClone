package functions;


import java.util.function.Function;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A wrapper for a memoized function.
 * Like the original implementation in lodash, the memo is exposed, and can
 * be modified by the programmer.
 * @author Justis
 */
public class MemoizedFunction<T, R> extends Memoized<T, R> implements Function<T, R> {
    
    private final Function<T, R> func;
    
    /**
     * Creates a memoized function from a function.
     * @param func The function to wrap.
     */
    public MemoizedFunction(Function<T, R> func){
        this.func = func;
    }
    
    @Override
    public R apply(T t) {
        return getMemo().computeIfAbsent(t, func);
    }
}
