/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Objects;

/**
 * An immutable pair.
 * @author Justis
 */
public class Pair<T, U>{
    private final T first;
    private final U second;
    
    /**
     * Creates a pair.
     * @param first The first in the pair.
     * @param second The second in the pair.
     */
    private Pair(T first, U second){
        this.first = first;
        this.second = second;
    }
    
    /**
     * Creates a pair.
     * @param <T> The type of the first in the pair.
     * @param <U> The type of the second in the pair.
     * @param first The first in the pair.
     * @param second The second in the pair.
     * @return The created pair.
     */
    public static <T, U> Pair of(T first, U second){
        return new Pair(first, second);
    }
    
    /**
     * Get the first in the pair.
     * @return The first of the pair.
     */
    public T first(){
        return first;
    }
    
    /**
     * Get the second in the pair.
     * @return The second of the pair.
     */
    public U last(){
        return second;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Pair){
            Pair pair = (Pair)obj;
            return Objects.equals(first, pair.first) && 
                    Objects.equals(second, pair.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(first);
        hash = 19 * hash + Objects.hashCode(second);
        return hash;
    }

    @Override
    public String toString(){
        return "[" + Objects.toString(first) + "," + Objects.toString(second) + "]";
    }
}
