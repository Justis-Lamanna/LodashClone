/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.util.Map;

/**
 * A predicate to use during map predicates.
 * @author Justis
 */
public interface MapPredicate<K, V> extends CollectionPredicate<V, K, Map<K, V>> {
    
}
