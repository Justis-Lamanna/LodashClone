/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lodash;

import java.util.List;
import sequence.ListChain;

/**
 * Chaining methods
 * @author Justis
 */
public class Seq {
    
    /**
     * Wraps a list so it can be modified with chainable methods.
     * @param <T> The type contained in the list.
     * @param list The list to wrap.
     * @return The chainable list.
     */
    public static <T> ListChain<T> chain(List<T> list){
        return new ListChain<>(list);
    }
}
