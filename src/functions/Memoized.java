/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that encapulates memoization.
 * @author Justis
 * @param <K> The type of the key of the memo.
 * @param <V> The type of the value of the memo.
 */
public class Memoized<K, V> {
    
    private Map<K, V> memo;
    
    public Memoized(){
        this.memo = new HashMap<>();
    }
    
    public Map<K, V> getMemo(){
        return memo;
    }
    
    public void setMemo(Map<K, V> memo){
        this.memo = memo;
    }
}
