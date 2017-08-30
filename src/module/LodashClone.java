/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import functions.DebounceConsumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
        DebounceConsumer<Integer> function = Functions.throttle((Integer t) -> System.out.println(t), 1000);
        for(int index = 0; index < 1_00000; index++){
            for(int index2 = 0; index2 < 1_00000; index2++){
                function.accept(index);
            }
        }
        long val = System.currentTimeMillis();
        while(System.currentTimeMillis() - val < 1000){
            
        }
        function.cancel();
    }
}
