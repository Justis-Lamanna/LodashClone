/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Builder class to help create maps easier.
* @param <K> The type of the key.
* @param <V> The type of the value.
*/
public class MapBuilder<K, V>{
   private Map<K, V> map;

   /**
    * Create a MapBuilder with an empty HashMap.
    */
   public MapBuilder(){
       this.map = new HashMap<>();
   }

   /**
    * Create a MapBuilder with a starting map.
    * @param map The initial map.
    */
   public MapBuilder(Map<K, V> map){
       this.map = map;
   }

   /**
    * Add an entry to the map.
    * @param key The key to add.
    * @param value The value to add.
    * @return This MapBuilder.
    */
   public MapBuilder put(K key, V value){
       map.put(key, value);
       return this;
   }

   /**
    * Add a number of keys and values to the map.
    * If the list sizes don't match in size, the longer list is
    * truncated to the shorter list's size.
    * @param keys The list of keys.
    * @param values The list of values.
    * @return This MapBuilder.
    */
   public MapBuilder put(List<K> keys, List<V> values){
       int length = Math.min(keys.size(), values.size());
       for(int index = 0; index < length; index++){
           map.put(keys.get(index), values.get(index));
       }
       return this;
   }

   /**
    * Retrieve the contained map.
    * @return The built map.
    */
   public Map<K, V> map(){
       return map;
   }
}
