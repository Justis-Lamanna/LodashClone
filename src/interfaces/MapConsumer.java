/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Map;

/**
 * A consumer used in map consuming.
 * @author Justis
 */
public interface MapConsumer<K, V> extends CollectionConsumer<V, K, Map<K, V>> {
    
}
