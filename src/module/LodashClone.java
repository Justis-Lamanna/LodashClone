/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lodash.Maps;
import lodash.Maths;
import lodash.Numbers;
import utils.MapBuilder;

/**
 *
 * @author justislamanna
 */
public class LodashClone {
    private LodashClone(){
        throw new IllegalStateException("Cannot instantiate Lodash");
    }
    
    public static void main(String[] args){
        List<Integer> keys = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> values = Arrays.asList(6, 7, 8, 9, 10);
        Map<Integer, Integer> map = new MapBuilder<Integer, Integer>()
                .put(keys, values).map();
        Map<Integer, Integer> map2 = new MapBuilder<Integer, Integer>()
                    .put(values, keys).map();
        System.out.println(
                Maps.values(map)
        );
        System.out.println(
                Maps.keys(map)
        );
    }
}
