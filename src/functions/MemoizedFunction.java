package functions;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A wrapped for a memoized function.
 * Like the original implementation in lodash, the memo is exposed, and can
 * be modified by the programmer.
 * @author Justis
 */
public class MemoizedFunction<T, R> implements Function<T, R> {
    
    private final Function<T, R> func;
    private Map<T, R> memo;
    
    /**
     * Creates a memoized function from a function.
     * @param func The function to wrap.
     */
    public MemoizedFunction(Function<T, R> func){
        this.func = func;
        this.memo = new HashMap<>();
    }
    
    @Override
    public R apply(T t) {
        return memo.computeIfAbsent(t, func);
    }
    
    /**
     * Get the current memo.
     * @return The memo.
     */
    public Map<T, R> getMemo(){
        return this.memo;
    }
    
    /**
     * Set the memo to use.
     * @param memo The memo to use.
     */
    public void setMemo(Map<T, R> memo){
        this.memo = memo;
    }
}
