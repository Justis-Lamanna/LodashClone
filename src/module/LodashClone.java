/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import functions.MemoizedFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lodash.Collections;
import lodash.Dates;
import lodash.Functions;

/**
 *
 * @author justislamanna
 */
public class LodashClone {
    private LodashClone(){
        throw new IllegalStateException("Cannot instantiate Lodash");
    }
    
    public static void main(String[] args){
        MemoizedFunction<Integer, Integer> function = Functions.memoize(i -> i * i);
        for(int index = 0; index < 7; index++){
            function.apply(index);
        }
        System.out.println(function.getMemo());
    }
}
